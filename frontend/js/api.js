const API_URL = "http://localhost:8081/books";
let allBooks = [];

let currentPage = 1;
const booksPerPage = 5;

async function getBooks() {
    try {
        const response = await fetch(API_URL);
        const books = await response.json();
        const totalBooks = document.getElementById("totalBooks");
        if (totalBooks) {
            totalBooks.innerText = books.length;
        }
        const totalBooksStats = document.getElementById("totalBooksStats");
        if (totalBooksStats) {
            totalBooksStats.innerText = books.length;
        }
        const authors = [...new Set(books.map(book => book.author))];
        const totalAuthors = document.getElementById("totalAuthors");
        if (totalAuthors) {
            totalAuthors.innerText = authors.length;
        }
        const list = document.getElementById("recentBooks");
        if (list) {
            list.innerHTML = "";
            books.slice(-5).reverse().forEach(book => {
                list.innerHTML += `
                    <li class="list-group-item">
                        📖 ${book.title}
                        <br>
                        <small>${book.author}</small>
                    </li>
                `;
            });
        }
    } catch (error) {
        console.error(error);
    }
}

async function loadBooks() {
    try {
        const response = await fetch(API_URL);
        allBooks = await response.json();
        displayBooks(allBooks);
    } catch (error) {
        console.error(error);
        alert("Unable to load books.");
    }
}

function displayBooks(books) {
    const start = (currentPage - 1) * booksPerPage;
    const end = start + booksPerPage;
    const paginatedBooks = books.slice(start, end);
    let rows = "";
    paginatedBooks.forEach(book => {
        rows += `
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>
                <button
                    class="btn btn-info btn-sm"
                    onclick="viewBook(${book.id})">
                    View
                </button>
                <button
                    class="btn btn-warning btn-sm"
                    onclick="editBook(${book.id})">
                    Edit
                </button>
                <button
                    class="btn btn-danger btn-sm"
                    onclick="deleteBook(${book.id})">
                    Delete
                </button>
            </td>
        </tr>
        `;
    });
    document.getElementById("bookTable").innerHTML = rows;
    document.getElementById("pageNumber").innerText =
        `Page ${currentPage}`;
}

async function saveBook() {
    const title = document.getElementById("title").value.trim();
    const author = document.getElementById("author").value.trim();
    if (title === "" || author === "") {
        alert("Please enter both title and author.");
        return;
    }
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: title,
                author: author
            })
        });
        if (response.ok) {
            showToast("Book Added Successfully!");
            setTimeout(() => {

    window.location.href = "books.html";

}, 1500);
        } else {
            alert("❌ Failed to add book.");
        }
    } catch (error) {
        console.error(error);
        alert("Cannot connect to Spring Boot Backend.");
    }
}

function editBook(id){
    window.location.href =
        "edit-book.html?id=" + id;

}
async function viewBook(id){

    const response = await fetch(API_URL + "/" + id);

    const book = await response.json();

    document.getElementById("viewId").innerText = book.id;

    document.getElementById("viewTitle").innerText = book.title;

    document.getElementById("viewAuthor").innerText = book.author;

    const modal = new bootstrap.Modal(
        document.getElementById("viewBookModal")
    );

    modal.show();

}

async function loadBook(){
    const params =
        new URLSearchParams(window.location.search);
    const id = params.get("id");
    const response =
        await fetch(API_URL + "/" + id);
    const book =
        await response.json();
    document.getElementById("bookId").value = book.id;
    document.getElementById("title").value = book.title;
    document.getElementById("author").value = book.author;
}

async function deleteBook(id) {
    const confirmDelete = confirm("Are you sure you want to delete this book?");
    if (!confirmDelete) {
        return;
    }
    try {
        const response = await fetch(API_URL + "/" + id, {
            method: "DELETE"
        });
        if (response.ok) {
            showToast("Book Deleted Successfully!");
            loadBooks();
        } else {
            alert("❌ Failed to delete book.");
        }
    } catch (error) {
        console.error(error);
        alert("Unable to connect to Spring Boot Backend.");
    }
}

async function updateBook(){
    const id =
        document.getElementById("bookId").value;
    const title =
        document.getElementById("title").value;
    const author =
        document.getElementById("author").value;
    const response =
        await fetch(API_URL + "/" + id, {
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            title:title,
            author:author
        })
    });
    if(response.ok){
        showToast("Book Updated Successfully!");
        setTimeout(() => {

    window.location.href = "books.html";

}, 1500);
    }else{
        alert("Update Failed");
    }
}

function nextPage() {
    if(currentPage * booksPerPage < allBooks.length){
        currentPage++;
        displayBooks(allBooks);
    }
}

function previousPage(){
    if(currentPage > 1){
        currentPage--;
        displayBooks(allBooks);
    }
}

function searchBooks() {
    const keyword = document
        .getElementById("searchBook")
        .value
        .toLowerCase();
    const filteredBooks = allBooks.filter(book =>
        book.title.toLowerCase().includes(keyword) ||
        book.author.toLowerCase().includes(keyword)
    );
    displayBooks(filteredBooks);
}

async function checkBackendStatus() {

    const status = document.getElementById("backendStatus");

    if (!status) {
        return;
    }

    try {

        const response = await fetch(API_URL);

        if (response.ok) {

            status.innerHTML = "🟢 Online";
            status.className = "text-success";

        } else {

            throw new Error();

        }

    } catch (error) {

        status.innerHTML = "🔴 Offline";
        status.className = "text-danger";

    }

}

function showToast(message, type = "success") {

    const toastElement = document.getElementById("liveToast");

    const toastMessage = document.getElementById("toastMessage");

    toastMessage.innerText = message;

    toastElement.className =
        "toast align-items-center text-bg-" +
        type +
        " border-0";

    const toast =
        new bootstrap.Toast(toastElement);

    toast.show();

}