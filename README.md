# circuit-breaker

A simple project to implement a circuit breaker pattern using Spring Boot and Resilience4j with a fallback method using Redis.

The project idea is to create a REST API that simulates a service that may fail. When the service fails, the circuit breaker will open and redirect requests to a fallback method that retrieves data from Redis. The api will simulate an app for post management, we will have a title, an image and the text of the post and also a button to show the author and the comments of the post.

## Endpoints

- `GET /api/v1/publications`: Retrieves a list of publications. This **not** endpoint is protected by a circuit breaker.
- `GET /api/v1/publications/{id}`: Retrieves a specific publication by its ID and all their comments. This endpoint is **not**  protected by a circuit breaker.
- `POST /api/v1/publications`: Creates a new post. This endpoint is **not** protected by a circuit breaker.
   `GET /api/v1/comments/{id}`: Retrieves a list of comments of a specific publication by its the publication ID. This endpoint **is protected** by a circuit breaker.

Run the application to see with more details:
- `http://localhost:8080/swagger-ui/index.html`

## Services

- `PublicationService`: Handles the business logic for managing publications.
- `CommentService`: Handles the business logic for managing comments, including saving and retrieving comments in Redis while the system is offline. (This is a mock)

## Technologies Used

- Spring Boot 3.5.6
- Resilience4j
- Redis
- MongoDB
- Spring Cloud OpenFeign
- Lombok
- Maven
- Java 21
- Docker (for running Redis and Mongodb)
- Postman (for testing the API)
- WireMock (for mocking external services)
- Swagger (for documentation)

## How to Run the Project

1. Clone the repository.
2. Make sure you have Docker installed and running.
3. Run the following command to start Redis and MongoDB using Docker:
   ```
   docker-compose up -d
   ```
4. Navigate to the project directory and run the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```
5. Use Postman or any other API testing tool to interact with the endpoints.
6. To stop the Docker containers, run:
   ```
   docker-compose down
   ```

## Note
The external service is mocked using WireMock for demonstration purposes.
- If you want to test it, put this JSON inside of the mappings folder:
  ```json
  {
    "request": {
      "method": "GET",
      "url": "/api/v1/comments/{publicationId}"
    },
    "response": {
      "status": 200,
      "headers": {
        "Content-Type": "application/json"
      },
      "jsonBody": [
        {
          "author": "Rafael",
          "text": "If you got here it means that the API is running fine, the wiremock worked well and the circuit is closed"
        }
      ]
    }
  }
  ```
