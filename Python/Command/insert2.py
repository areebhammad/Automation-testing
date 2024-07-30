import pandas as pd
import os

# Load the CSV file
file_path = 'Series.csv'
output_file_path = 'in.txt'

# Check if input file exists
if not os.path.exists(file_path):
    raise FileNotFoundError(f"The input file at '{file_path}' was not found.")

# Ensure the output directory exists
output_dir = os.path.dirname(output_file_path)
if output_dir and not os.path.exists(output_dir):
    os.makedirs(output_dir)

# Read the CSV file into a DataFrame
series_df = pd.read_csv(file_path)

# Ensure all data are strings and handle NaN values
series_df = series_df.fillna('')

# Initialize a list to store SQL commands
insert_commands = []

# Iterate over each row in the DataFrame
for _, row in series_df.iterrows():
    command = f"""
    ('{str(row['slug']).strip()}', '{str(row['title']).strip()}', '{str(row['title_eng']).strip()}', '{str(row['link']).strip()}', '{str(row['year']).strip()}', 
     '{str(row['description']).strip()}', {int(row['releaseYear'])}, '{str(row['rating']).strip()}', {float(row['star'])}, '{str(row['runtime']).strip()}', 
     '{str(row['network']).strip()}', '{str(row['status']).strip()}', '{str(row['trailer']).strip()}', {float(row['recommendationScore'])})
    """
    insert_commands.append(command)

# Combine all individual insert commands into a single SQL command
full_insert_command = "INSERT INTO Series (slug, title, title_eng, link, year, description, releaseYear, rating, star, runtime, network, status, trailer, recommendationScore) VALUES\n" + ",\n".join(insert_commands) + ";"

# Write the full insert command to a file using UTF-8 encoding
with open(output_file_path, 'w', encoding='utf-8') as output_file:
    output_file.write(full_insert_command)

# Optionally, print the full insert command to the console
print(full_insert_command)
