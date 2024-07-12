from app import app
from models.user_model import user_model
from flask import request, jsonify

obj = user_model()

@app.route('/user/signup')
def signup():
    return 'Sign Up'

@app.route('/user/sign')
def newsignup():
    return obj.user_signup_model()

@app.route('/user/details')
def getdetails():
    return obj.user_getdetails_model()

# @app.route('/user/adduser', methods=['POST'])
# def adddetails():
#     if request.method == 'POST':
#         try:
#             data = request.form
#             response = obj.add_user_details_model(data)
#             return jsonify({'message': response}), 200
#         except Exception as e:
#             return jsonify({'error': str(e)}), 500
#     else:
#         return jsonify({'error': 'Method Not Allowed'}), 405

# @app.route('/user/deleteuser', methods=['DELETE'])
# def deletedetails():
#     if request.method == 'DELETE':
#         data = request.form
#         response = obj.delete_user_details_model(data)
#         return jsonify({'message': response})

@app.route("/user/adduser" ,methods=["POST"])
def addUser():
    print(request.form)
    return obj.add_user_details_model(request.form)
    


@app.route('/user/deleteuser/<int:id>',methods=["DELETE"])
def delete_user(id):
    print(id)
    return obj.user_delete_model(id)

# Ensure the app is run only when this file is executed directly
if __name__ == '__main__':
    app.run(debug=True)

