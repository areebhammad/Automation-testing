import mysql.connector as sql
import json
# from requests.models import make_response
import jsonify

class user_model:
    def __init__(self):
        self.db = sql.connect(host="localhost", user="root", passwd="root", database="mydb")
        self.cursor = self.db.cursor()
        if self.db:
            print("Database Connected Successfully")
        else:
            print("Database Connection Failed")
    
    def user_signup_model(self):
        return 'This is a sign Up form model'
    
    def user_getdetails_model(self):
        self.cursor.execute("SELECT * FROM user")
        result = self.cursor.fetchall()
        print(result)
        if len(result) > 0:
            # return json.dumps(result)
            return {'payload': result}
            # return make_response(jsonify({'payload': result}), 200)
        else:
            return "No Data Found"
    
    def add_user_details_model(self, data):
        try:
            query = "INSERT INTO user(name, address) VALUES(%s, %s)"
            values = (data['name'], data['address'])
            self.cursor.execute(query, values)
            self.db.commit()  
            return "User Details Added Successfully"
        except Exception as e:
            self.db.rollback()  # Rollback in case of any exception
            return f"Error adding user details: {str(e)}"
    
    def user_delete_model(self, id):
        query = "DELETE FROM user WHERE id = %s"
        values = (id,)
        self.cursor.execute(query, values)
        self.db.commit() 
        if self.cursor.rowcount > 0:
            return "User Deleted Successfully"
        else:
            return "User Not Found"
        

# # Using a while loop

# cursor.execute("SELECT * FROM employees")

# row = cursor.fetchone()

# while row is not None:

#   print(row)

#   row = cursor.fetchone()
 
# # Using the cursor as iterator

# cursor.execute("SELECT * FROM employees")

# for row in cursor:

#   print(row)
 