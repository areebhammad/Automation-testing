import csv

def check_repeated_names(csv_file_path):
    name_lines = {}  # Dictionary to track names and their line numbers
    duplicate_names = {}  # Dictionary to store names and their duplicate line numbers

    with open(csv_file_path, 'r') as file:
        reader = csv.reader(file)
        
        for line_number, row in enumerate(reader, start=1):
            if not row:  # Skip empty lines
                continue
            
            name = row[1].strip()  # Assume names are in the first column

            if name in name_lines:
                if name not in duplicate_names:
                    duplicate_names[name] = name_lines[name]  # Store the first occurrence
                duplicate_names[name].append(line_number)
            else:
                name_lines[name] = [line_number]

    # Print results
    if duplicate_names:
        for name, lines in duplicate_names.items():
            print(f"Name '{name}' is repeated on lines: {', '.join(map(str, lines))}")
    else:
        print("No duplicate names found.")

# Example usage
csv_file_path = './Series.csv'  # Replace with your CSV file path
check_repeated_names(csv_file_path)
