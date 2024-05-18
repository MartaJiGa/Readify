# Readify

This project is a library management system developed using MySQL. It includes entities for managing books, users, and loans.

## Table of Contents

- [Introduction](#introduction)
- [Database Schema](#database-schema)
- [Installation](#installation)
- [Usage](#usage)
- [Example Queries](#example-queries)
- [Contributing](#contributing)
- [License](#license)

## Introduction

**Readify** is designed to help manage a library's inventory, user registrations, and book loans efficiently. The database schema includes three main entities: `Book`, `User`, and `Loan`.

## Database Schema

### Book Table

- **id** INT AUTO_INCREMENT PRIMARY KEY
- **title** VARCHAR(255) NOT NULL
- **author** VARCHAR(255) NOT NULL
- **publishedDate** DATE
- **ISBN** VARCHAR(13)
- **available** BOOLEAN
- **pageCount** INT 

### User Table

- **id**: INT AUTO_INCREMENT PRIMARY KEY
- **name**: VARCHAR(255) NOT NULL
- **email**: VARCHAR(255) NOT NULL
- **membershipDate**: DATE
- **active**: BOOLEAN
- **role**: INT

### Loan Table

- **id**: INT AUTO_INCREMENT PRIMARY KEY
- **bookId**: INT
- **userId**: INT
- **loanDate**: DATE
- **returnDate**: DATE
- **returned**: BOOLEAN
- **FOREIGN KEY (bookId)**: REFERENCES Book(id)
- **FOREIGN KEY (userId)**: REFERENCES User(id)

### Books Data
    INSERT INTO Book (title, author, publishedDate, ISBN, available, pageCount) VALUES 
    ('El Señor de los Anillos', 'J.R.R. Tolkien', '1954-07-29', '978-84-450-7084-9', 1, 1178),
    ('Cien años de soledad', 'Gabriel García Márquez', '1967-05-30', '978-84-376-0494-7', 1, 417),
    ('Harry Potter y la piedra filosofal', 'J.K. Rowling', '1997-06-26', '978-84-9838-093-4', 1, 352),
    ('1984', 'George Orwell', '1949-06-08', '978-84-666-5339-5', 1, 328),
    ('Orgullo y prejuicio', 'Jane Austen', '1813-01-28', '978-84-15702-91-1', 1, 464);

### Users Data
    INSERT INTO User (name, email, membershipDate, active, role) VALUES 
    ('John Doe', 'john@example.com', '2022-05-17', true, 1),
    ('Jane Smith', 'jane@example.com', '2022-05-17', true, 2),
    ('Alice Johnson', 'alice@example.com', '2022-05-17', false, 2),
    ('Bob Brown', 'bob@example.com', '2022-05-17', true, 1),
    ('Emily Davis', 'emily@example.com', '2022-05-17', true, 3);

### Loan Data
    INSERT INTO Loan (userId, bookId, loanDate, returnDate, returned) VALUES
    (1, 3, '2024-05-01', '2024-05-15', false),
    (2, 2, '2024-04-20', '2024-05-10', true),
    (3, 1, '2024-05-05', '2024-05-20', false);

## Installation

To set up Readify API, follow these steps:

1. Clone the repository:
    
        git clone https://github.com/Dviela/Readify.git
        cd readify
    

3. Set up the MySQL database:
    ```sql
    CREATE DATABASE Library;
    USE Library;

    CREATE TABLE Book (
        book_id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        genre VARCHAR(100),
        publication_date DATE,
        isbn VARCHAR(20) UNIQUE NOT NULL,
        quantity INT NOT NULL
    );

    CREATE TABLE User (
        user_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        address VARCHAR(255),
        phone VARCHAR(20),
        email VARCHAR(100) UNIQUE NOT NULL,
        registration_date DATE NOT NULL DEFAULT CURRENT_DATE
    );

    CREATE TABLE Loan (
        loan_id INT AUTO_INCREMENT PRIMARY KEY,
        book_id INT,
        user_id INT,
        loan_date DATE NOT NULL DEFAULT CURRENT_DATE,
        return_date DATE,
        status ENUM('loaned', 'returned') NOT NULL DEFAULT 'loaned',
        FOREIGN KEY (book_id) REFERENCES Book(book_id),
        FOREIGN KEY (user_id) REFERENCES User(user_id)
    );
    ```


4. Update `application.properties` with your MySQL configuration:
   
            
            spring.datasource.url=jdbc:mysql://localhost:3306/Library
            spring.datasource.username=root
            spring.datasource.password=yourpassword
            spring.jpa.hibernate.ddl-auto=update
            

5. Build and run the project using Maven:
    
        mvn clean install
        mvn spring-boot:run
    

## Usage

The API provides endpoints for managing books, users, and loans. Below are the details of the available endpoints.

## API Endpoints

### Book
    
    - **Create Book**: `POST /api/books`
    - **Get All Books**: `GET /api/books`
    - **Get Book by ID**: `GET /api/books/{id}`
    - **Update Book**: `PUT /api/books/{id}`
    - **Delete Book**: `DELETE /api/books/{id}`
    
### User
    
    - **Create User**: `POST /api/users`
    - **Get All Users**: `GET /api/users`
    - **Get User by ID**: `GET /api/users/{id}`
    - **Update User**: `PUT /api/users/{id}`
    - **Delete User**: `DELETE /api/users/{id}`
    
### Loan
    
    - **Create Loan**: `POST /api/loans`
    - **Get All Loans**: `GET /api/loans`
    - **Get Loan by ID**: `GET /api/loans/{id}`
    - **Update Loan**: `PUT /api/loans/{id}`
    - **Delete Loan**: `DELETE /api/loans/{id}`
    
## Example Requests

### Create a Book

    
    curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d '{
    "title": "The Odyssey",
    "author": "Homer",
    "publishedDate": "1967-06-05",
    "isbn": "978-84-670-5005-9",
    "available": false,
    "pageCount": 448 
    }'

### Create a User

    curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "membershipDate": "2023-05-05",
    "active": false,
    "role": 1    
    }'

### Create a Loan

    curl -X POST http://localhost:8080/api/loans -H "Content-Type: application/json" -d '{
    "book_id": 1,
    "user_id": 1,
    "loanDate": "2024-05-01",
    "returnDate": "2024-05-15",
    "returned": true
    }'

### PHP
### Project Structure

    project-root/
    ├── config/
    │ └── db_config.php
    ├── css/
    │ └── styles.css
    ├── views/
    │ ├── users.php
    │ └── books.php
    ├── index.php
    └── ...
    

- **config/**: Contains the db_config.php file with the database configuration.
- **css/**: Contains the styles.css file for the application styles.
- **views/**: Contains PHP views for listing users and books.
- **index.php**: Homepage of the application.

### Technologies Used
The project utilizes the following technologies:

PHP: Programming language used for server-side logic.
HTML: Used for the structure of web pages.
CSS: Used for styling web pages.

### Database Configuration
The db_config.php file in the config/ directory contains the configuration for the MySQL database used by the application.

### Running the Project
To run the project, ensure you have a web server and PHP installed in your development environment. Then, you can access the homepage at http://localhost:port/index.php.
Remember to have XAMPP active along with MySQL for the database to function correctly.
