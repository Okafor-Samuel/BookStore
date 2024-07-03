### README


# Bookstore RESTful Web Service

This is a RESTful web service for managing a bookstore, built with Spring Boot. It supports CRUD operations for books, authors, and genres.

## Requirements

- Java 11
- MySQL
- Maven

## Setup Instructions

1. Clone the repository:

    ```sh
    git clone https://github.com/yourusername/bookstore.git
    cd bookstore
    ```

2. Configure the database in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
    spring.datasource.username=root
    spring.datasource.password=root
    ```

3. Create the database:

    ```sql
    CREATE DATABASE bookstore;
    ```

4. Build and run the application:

    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## API Documentation

### Authors

- **GET /api/v1/authour/getAllAuthors**: Get all authors
- **GET /api/v1/authour/getById**: Get author by ID
- **POST /api/v1/authour/createAuthor**: Create new author
- **PUT /api/v1/authour/updateAuthor**: Update author
- **DELETE /api/v1/authour/delete**: Delete author

### Books

- **GET /api/books**: Get all books
- **GET /api/books/{id}**: Get book by ID
- **POST /api/books**: Create new book
- **PUT /api/books/{id}**: Update book
- **DELETE /api/books/{id}**: Delete book

### Genres

- **GET /api/genres**: Get all genres
- **GET /api/genres/{id}**: Get genre by ID
- **POST /api/genres**: Create new genre
- **PUT /api/genres/{id}**: Update genre
- **DELETE /api/genres/{id}**: Delete genre

