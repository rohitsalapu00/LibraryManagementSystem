window.onload = function () {

    // Dashboard (index.html)
    if (document.getElementById("totalBooks")) {
        getBooks();
        checkBackendStatus();
    }

    // Books page (books.html)
    if (document.getElementById("bookTable")) {
        loadBooks();
    }

    // Edit Book page (edit-book.html)
    if (document.getElementById("bookId")) {
        loadBook();
    }

};