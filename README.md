### README


# Bookstore RESTful Web Service

This is a RESTful web service for managing a bookstore, built with Spring Boot. It supports CRUD operations for books, authors, and genres.

## Requirements

- Java 17
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
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/bookstore
    spring.datasource.username=${MYSQL_USER:root}
    spring.datasource.password=${MYSQL_PASSWORD:root}
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
- **GET /api/v1/authour/getById/{id}**: Get author by ID
- **POST /api/v1/authour/createAuthor**: Create new author
- **PUT /api/v1/authour/updateAuthor/{id}**: Update author
- **DELETE /api/v1/authour/delete/{id}**: Delete author

### Books

- **GET /api/v1/book/getAllBooks**: Get all books
- **GET /api/v1/book/getByid/{id}**: Get book by ID
- **GET /api/v1/book/getByTitle/{title}**: Get book by Title
- **GET /api/v1/book/getByPublishedDate/{pulishedDate}**: Get book by Published Date
- **GET /api/v1/book/getByPrice/{price}**: Get book by Price
- **GET /api/v1/book/getByLanguage/{language}**: Get book by Language
- **POST /api/v1/book/**: Create new book
- **PUT /api/v1/book/updateGenre{id}**: Update book
- **DELETE /api/v1/book/delete/{id}**: Delete book

### Genres

- **GET /api/v1/genre/getAllGenre**: Get all genre
- **GET /api/v1/genre/getById/{id}**: Get genre by ID
- **POST /api/v1/genre/createGenre**: Create new genre
- **PUT /api/v1/genre/updateGenre/{id}**: Update genre
- **DELETE /api/v1/genre/delete/{id}**: Delete genre

