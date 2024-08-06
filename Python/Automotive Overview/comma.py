import csv

# Function to process the CSV file
def process_csv(input_file, output_file):
    with open(input_file, 'r') as infile, open(output_file, 'w', newline='') as outfile:
        reader = csv.reader(infile)
        writer = csv.writer(outfile)
        
        for row in reader:
            # Join the row elements with a comma
            new_row = [item.replace(' ', ',') for item in row]
            writer.writerow(new_row)

# Example usage
input_csv_file = 'SeriesGenres.csv'  # Replace with your input file name
output_csv_file = 'output.csv'  # Replace with your desired output file name

process_csv(input_csv_file, output_csv_file)
