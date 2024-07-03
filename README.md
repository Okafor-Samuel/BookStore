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
    spring.datasource.url=${DATABASE_URL}
    spring.datasource.username=${DATABASE_USERNAME}
    spring.datasource.password=${DATABASE_PASSWORD}
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

## Environmental Configuration

Instead of hardcoding the database configuration in the `application.properties` file, it's a good practice to externalize these configurations using environment variables. Here's how you can do it:

1. Remove the hardcoded database configuration from `src/main/resources/application.properties`:

    ```properties
    #spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
    #spring.datasource.username=root
    #spring.datasource.password=root
    ```

2. Use environment variables for database configuration:

    ```properties
    spring.datasource.url=${DATABASE_URL}
    spring.datasource.username=${DATABASE_USERNAME}
    spring.datasource.password=${DATABASE_PASSWORD}
    ```

3. Set the environment variables before running the application:

    ```sh
    export DATABASE_URL=jdbc:mysql://localhost:3306/bookstore
    export DATABASE_USERNAME=root
    export DATABASE_PASSWORD=root
    mvn clean install
    mvn spring-boot:run
    ```

By following these steps, you can keep your database credentials secure and make your application more flexible and portable.

## Screenshots

(Screenshots of the application in use, showing key features and functionality.)

1. **Authors List**: 
<img src="https://github.com/Okafor-Samuel/BookStore/blob/main/Screen%20Shot%202024-07-03%20at%201.55.10%20PM.png" />

3. **Books List**: 
<img src="https://github.com/Okafor-Samuel/BookStore/blob/main/Screen%20Shot%202024-07-03%20at%202.04.41%20PM.png" />

4. **Genres List**: 
<img src ="https://github.com/Okafor-Samuel/BookStore/blob/main/Screen%20Shot%202024-07-03%20at%201.58.57%20PM.png" />

5. **Create Author**: 
<img src ="https://github.com/Okafor-Samuel/BookStore/blob/main/Screen%20Shot%202024-07-03%20at%202.00.37%20PM.png" />

6. **Update Book**: 
<img src ="https://github.com/Okafor-Samuel/BookStore/blob/main/Screen%20Shot%202024-07-03%20at%202.02.45%20PM.png" />

## Contact

For any issues or inquiries, please contact [Samuel Okafor](mailto:Okaforsamuel1000@gmail.com).

This comprehensive README should help you set up, understand, and utilize the Bookstore RESTful Web Service efficiently.


