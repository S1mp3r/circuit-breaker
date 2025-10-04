# circuit-breaker

A simple project to implement a circuit breaker pattern using Spring Boot and Resilience4j with a fallback method using Redis.

The project idea is to create a REST API that simulates a service that may fail. When the service fails, the circuit breaker will open and redirect requests to a fallback method that retrieves data from Redis. The api will simulate an app for post management, we will have a title, an image and the text of the post and also a button to show the author and the comments of the post.

## Endpoints

- `GET /posts`: Retrieves a list of posts. This endpoint is protected by a circuit breaker.
- `GET /posts/{id}`: Retrieves a specific post by its ID and all their comments. This endpoint is also protected by a circuit breaker.
- `POST /posts`: Creates a new post. This endpoint is not protected by a circuit breaker.

## Services

- `PublicationService`: Handles the business logic for managing posts, including fetching posts from an external service and saving posts to Redis.
- `CommentService`: Manages interactions with Redis, including saving and retrieving posts. (This is a mock)
