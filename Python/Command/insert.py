import pandas as pd

# Load the CSV file
file_path = 'Series.csv'
series_df = pd.read_csv(file_path)

# Ensure all data are strings and handle NaN values
series_df = series_df.fillna('')

insert_commands = []

for _, row in series_df.iterrows():
    command = f"""
    ('{str(row['slug']).strip()}', '{str(row['title']).strip()}', '{str(row['title_eng']).strip()}', '{str(row['link']).strip()}', '{str(row['year']).strip()}', 
     '{str(row['description']).strip()}', {int(row['releaseYear'])}, '{str(row['rating']).strip()}', {float(row['star'])}, '{str(row['runtime']).strip()}', 
     '{str(row['network']).strip()}', '{str(row['status']).strip()}', '{str(row['trailer']).strip()}', {float(row['recommendationScore'])})
    """
    insert_commands.append(command)

# Combine all individual insert commands into a single SQL command
full_insert_command = "INSERT INTO Series (slug, title, title_eng, link, year, description, releaseYear, rating, star, runtime, network, status, trailer, recommendationScore) VALUES\n" + ",\n".join(insert_commands) + ";"

# Write the full insert command to a file



# Optionally, print the full insert command to the console
print(full_insert_command)
