// const csv = require("csv-parser");
// import { createClient } from "@libsql/client";

// export const turso = createClient({
//   url: process.env.TURSO_DATABASE_URL,
//   authToken: process.env.TURSO_AUTH_TOKEN,
// });

// const csvFilePath = "/Series.csv";

// fs.createReadStream(csvFilePath)
//   .pipe(csv())
//   .on("data", (row) => {
//     console.log(row);
//   });

import fs from "fs";
import csv from "csv-parser";
import { createClient } from "@libsql/client";
import dotenv from "dotenv";

dotenv.config();

// Create the client
const client = createClient({
  url: process.env.TURSO_DATABASE_URL,
  authToken: process.env.TURSO_AUTH_TOKEN,
});

// Path to the CSV file
const csvFilePath = "./Series.csv";

// Function to insert CSV data into Tursodb
const insertCsvData = async () => {
  const insertQueries = [];

  // Read CSV file
  fs.createReadStream(csvFilePath)
    .pipe(csv())
    .on("data", (row) => {
      const {
        slug,
        title,
        title_eng,
        link,
        year,
        description,
        releaseYear,
        rating,
        star,
        runtime,
        network,
        status,
        trailer,
        recommendationScore,
      } = row;

      // Create SQL insert query for each row
      insertQueries.push({
        sql: `
          INSERT INTO Series2 (
            slug, title, title_eng, link, year, description, releaseYear, rating, star, runtime, network, status, trailer, recommendationScore
          ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        `,
        args: [
          slug,
          title,
          title_eng,
          link.trim(),
          year,
          description.replace("'", "''"),
          parseInt(releaseYear),
          rating,
          parseFloat(star),
          runtime,
          network,
          status,
          trailer.trim(),
          parseFloat(recommendationScore),
        ],
      });
    })
    .on("end", async () => {
      // Execute batch insert
      try {
        const result = await client.batch(insertQueries, "write");
        console.log(
          "CSV file successfully processed and data inserted into Tursodb"
        );
      } catch (error) {
        console.error("Error inserting data into Tursodb:", error);
      } finally {
        client.close(); // Ensure the client is closed after operation
      }
    });
};

// Call the function to insert data
insertCsvData();
