# Pricing Service - Spring Boot Application

This project provides a REST API for querying pricing information. It uses an in-memory H2 database initialized with
sample data to demonstrate the functionality.

## Features

- Accepts as input: application date, product ID, and brand ID.
- Returns: product ID, brand ID, price list, start date, end date, and the applicable price.
- Database: H2 in-memory database with sample data.
- Swagger UI for testing and API documentation.

## Prerequisites

- Java 17
- Maven 3.x

## Running the Application

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/Cesarwth/testhb.git
   cd campaign

2. Build and run the project using Maven:
   ```bash
   ./mvnw spring-boot:run

3. The application will start on the default port 8080.
4. Access Swagger UI for testing the endpoints:
   ```bash
   http://localhost:8080/swagger-ui.html

## Usage

Once the application is running, you can test the /api/prices endpoint with Swagger or through any API client (e.g.,
Postman).

### Example Request

POST: /api/prices

```bash 
    {
        "applicationDate": "2020-06-14-10.00.00",
        "productId": 35455,
        "brandId": 1
    }
 ``` 

### Example Response

```
    {
        "productId": 35455,
        "brandId": 1,
        "priceList": 1,
        "startDate": "2020-06-14T10:00:00",
        "endDate": "2020-06-14T20:00:00",
        "price": 35.50
    }
 ``` 

## Database

The application uses an H2 in-memory database. The schema and sample data are automatically loaded at startup from
src/main/resources/data.sql. The H2 console is available at:

```bash
http://localhost:8080/h2-console
``` 

Use the following credentials to access the console:

```bash 
   JDBC URL: jdbc:h2:mem:testdb
   User: sa
   Password:
``` 

### Running Tests

To run the integration tests:

```bash 
./mvnw test
```

## API Documentation

The API documentation is available through Swagger at:

``` 
http://localhost:8080/swagger-ui.html
``` 

## Technologies Used

- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (In-Memory)**
- **Swagger (Springdoc OpenAPI)**
- **JUnit 5 (for testing)**