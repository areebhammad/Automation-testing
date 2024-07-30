import pandas as pd
import sqlite3
import os

# Load the CSV file
file_path = 'Series.csv'

# Check if input file exists
if not os.path.exists(file_path):
    raise FileNotFoundError(f"The input file at '{file_path}' was not found.")

# Read the CSV file into a DataFrame
series_df = pd.read_csv(file_path)

# Ensure all data are strings and handle NaN values
series_df = series_df.fillna('')

# Connect to SQLite database (or create it if it doesn't exist)
conn = sqlite3.connect('tursodb.db')  # Use the appropriate connection string for your database
cursor = conn.cursor()

# Create the Series table if it doesn't exist
cursor.execute('''
CREATE TABLE IF NOT EXISTS Series2 (
    slug TEXT,
    title TEXT,
    title_eng TEXT,
    link TEXT,
    year TEXT,
    description TEXT,
    releaseYear INTEGER,
    rating TEXT,
    star REAL,
    runtime TEXT,
    network TEXT,
    status TEXT,
    trailer TEXT,
    recommendationScore REAL
)
''')

# Insert data row by row
for _, row in series_df.iterrows():
    cursor.execute('''
    INSERT INTO Series (slug, title, title_eng, link, year, description, releaseYear, rating, star, runtime, network, status, trailer, recommendationScore)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    ''', (
        str(row['slug']).strip(),
        str(row['title']).strip(),
        str(row['title_eng']).strip(),
        str(row['link']).strip(),
        str(row['year']).strip(),
        str(row['description']).strip(),
        int(row['releaseYear']),
        str(row['rating']).strip(),
        float(row['star']),
        str(row['runtime']).strip(),
        str(row['network']).strip(),
        str(row['status']).strip(),
        str(row['trailer']).strip(),
        float(row['recommendationScore'])
    ))

# Commit the transaction and close the connection
conn.commit()
conn.close()

print("Data inserted successfully.")
