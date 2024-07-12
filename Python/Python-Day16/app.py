from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/<name>')
def hello_name(name):
    return 'Hello, %s!' % name

@app.route("/square/<int:num>")
def square(num):
    return str(num * num)

if __name__ == '__main__':
    app.run(debug=True)

import controller.user_controller as user_controller
