# Corrected library data
library_data = {
    "To Kill a Mockingbird": {"author": "Harper Lee", "genre": "Fiction"},
    "Moby Dick": {"author": "Herman Melville", "genre": "Adventure"},
    "Pride and Prejudice": {"author": "Jane Austen", "genre": "Romance"},
    "The Lord of the Rings": {"author": "J.R.R. Tolkien", "genre": "Fantasy"},
}

# Set to track borrowed books
borrowed_books = set()

def search_book(title):
    """
    Searches for a book by its title in the library data.
    """
    try:
        # Check if the book exists in the library data
        book_info = library_data[title]
    except KeyError:
        # If book is not found, raise a KeyError
        raise KeyError(f"Book with title '{title}' not found in the library.")
    finally:
        # No cleanup required in this case, but finally block is present
        pass
    return book_info

def borrow_book(title):
    """
    Attempts to borrow a book by its title.
    """
    try:
        if title in borrowed_books:
            # If the book is already borrowed, raise a ValueError
            raise ValueError(f"Book with title '{title}' is already borrowed.")
        elif title in library_data:
            # If the book is available and in library data, borrow it
            borrowed_books.add(title)
        else:
            # If the book is not found in the library data, raise a KeyError
            raise ValueError(f"Book with title '{title}' is not available in the library.")
    except ValueError as ve:
        # Handle ValueError
        raise ve
    finally:
        # No cleanup required in this case, but finally block is present
        pass

def return_book(title):
    """
    Attempts to return a book by its title.
    """
    try:
        if title in borrowed_books:
            borrowed_books.remove(title)
        else:
            raise ValueError(f"Book with title '{title}' was not borrowed.")
    except ValueError as ve:
        raise ve
    finally:
        pass

# Example Usage:
# Search for a book
try:
    book_info = search_book("Pride and Prejudice")
    print("Book found:", book_info)
except KeyError as e:
    print(e)

# Borrow a book
try:
    borrow_book("Moby Dick")
    print("Book borrowed successfully.")
except ValueError as e:
    print(e)

# Try to borrow the same book again
try:
    borrow_book("Moby Dick")
except ValueError as e:
    print(e)

# Return a book
try:
    return_book("Moby Dick")
    print("Book returned successfully.")
except ValueError as e:
    print(e)

# Try to return a book that was not borrowed
try:
    return_book("Moby Dick")
except ValueError as e:
    print(e)
