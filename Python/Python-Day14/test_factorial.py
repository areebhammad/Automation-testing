import pytest
from factorial import factorial

@pytest.mark.zero
def test_factorial_of_zero():
    assert factorial(0) == 1

@pytest.mark.test_factorial_of_positive_numbers
def test_factorial_of_positive_numbers():
    assert factorial(1) == 1
    assert factorial(2) == 2
    assert factorial(5) == 120
    # assert factorial(10) == 362880

@pytest.mark.test_factorial_of_negative_number
def test_factorial_of_negative_number():
    with pytest.raises(ValueError):
        factorial(-1)


@pytest.mark.test_factorial_of_large_number
def test_factorial_of_large_number():
    assert factorial(20) == 2432902008176640000
    assert factorial(30) == 265252859812191058636308480000000

# Additional test cases can be added as needed
