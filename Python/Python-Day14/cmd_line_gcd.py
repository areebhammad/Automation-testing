import sys

def gcd(n1, n2):
    while n2:
        n1, n2 = n2, n1 % n2
    return n1

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python program.py <number1> <number2>")
        sys.exit(1)

    number1 = int(sys.argv[1])
    number2 = int(sys.argv[2])

    print(gcd(number1, number2))
