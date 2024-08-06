import csv

def get_column_values(csv_file_path, column_name):
    """
    Extract values from the specified column in the CSV file.
    """
    values = set()
    with open(csv_file_path, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            if column_name in row:
                values.add(row[column_name].strip())
    return values

def find_missing_titles(file1_path, file2_path, column_name):
    """
    Find titles in file1 that are missing in file2.
    """
    titles_file1 = get_column_values(file1_path, column_name)
    titles_file2 = get_column_values(file2_path, column_name)
    
    missing_titles = titles_file1 - titles_file2
    
    if missing_titles:
        print(f"Titles in '{file1_path}' but missing in '{file2_path}':")
        for title in missing_titles:
            print(title)
    else:
        print(f"All titles from '{file1_path}' are present in '{file2_path}'.")

# Example usage
file1_path = 'Series.csv'  # Replace with the path to your first CSV file
file2_path = 'name.csv'  # Replace with the path to your second CSV file
column_name = 'title'     # Replace with the name of the column to compare

find_missing_titles(file1_path, file2_path, column_name)
