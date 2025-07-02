# Spring Boot RabbitMQ Concept

A Spring Boot 3 application demonstrating RabbitMQ messaging with a producer-consumer pattern. This project showcases how to set up and use RabbitMQ with Spring Boot, including Docker Compose for easy deployment.

## Features

- **Spring Boot 3.2** with Java 21
- **RabbitMQ** for message brokering
- **Docker Compose** for containerization
- **REST API** for sending messages
- **Environment variable** configuration
- **JSON message** serialization
- **Topic Exchange** with routing keys

## Prerequisites

- Java 21
- Maven 3.9.6+
- Docker 20.10.0+
- Docker Compose 2.0.0+

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/com/github/gescof/springbootrabbitmqconcept/
│   │   │   ├── config/         # Configuration classes
│   │   │   ├── controller/     # REST controllers
│   │   │   ├── model/          # Data models
│   │   │   └── service/        # Business logic
│   │   └── resources/
│   │       └── application.yml # Application configuration
│   └── test/                   # Test classes
├── .dockerignore
├── docker-compose.yml          # Docker Compose configuration
├── Dockerfile                  # Docker build configuration
└── pom.xml                     # Maven configuration
```

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd spring-boot-rabbitmq-concept
```

### 2. Run with Docker Compose (Recommended)

```bash
docker-compose up --build
```

This will start:
- Spring Boot application on port 8080
- RabbitMQ on port 5672 (AMQP) and 15672 (Management UI)

### 3. Run Locally (Without Docker)

1. Start RabbitMQ:
   ```bash
   docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
   ```

2. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Send a Message

```http
POST /api/messages?message={message}&sender={sender}
```

**Parameters:**
- `message`: The message content (required)
- `sender`: Sender identifier (default: "Anonymous")

**Example:**
```bash
curl -X POST "http://localhost:8080/api/messages?message=Hello%20RabbitMQ&sender=User1"
```

## Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_RABBITMQ_HOST` | RabbitMQ host | `rabbitmq` (Docker) / `localhost` (local) |
| `SPRING_RABBITMQ_PORT` | RabbitMQ port | `5672` |
| `SPRING_RABBITMQ_USERNAME` | RabbitMQ username | `guest` |
| `SPRING_RABBITMQ_PASSWORD` | RabbitMQ password | `guest` |
| `RABBITMQ_QUEUE_NAME` | Queue name | `demo_queue` |
| `RABBITMQ_EXCHANGE_NAME` | Exchange name | `demo_exchange` |
| `RABBITMQ_ROUTING_KEY` | Routing key | `demo_routing_key` |

### RabbitMQ Management UI

Access the RabbitMQ Management UI at: http://localhost:15672

- **Username:** guest
- **Password:** guest

## Development

### Build the Application

```bash
mvn clean package
```

### Run Tests

```bash
mvn test
```

## Architecture

### Components

1. **MessageProducer**: Handles sending messages to RabbitMQ
2. **MessageConsumer**: Listens for and processes messages from the queue
3. **MessageController**: Exposes REST endpoints for message operations
4. **RabbitMqConfig**: Configures RabbitMQ beans and connection settings

### Message Flow

1. Client sends HTTP POST request to `/api/messages`
2. `MessageController` receives the request and calls `MessageProducer`
3. `MessageProducer` publishes the message to RabbitMQ exchange
4. RabbitMQ routes the message to the appropriate queue based on routing key
5. `MessageConsumer` receives and processes the message

## Troubleshooting

### Common Issues

1. **Connection refused errors**
   - Ensure RabbitMQ is running
   - Check host and port configuration
   - Verify credentials if using authentication

2. **Message not being delivered**
   - Check queue and exchange bindings in RabbitMQ Management UI
   - Verify routing keys match between producer and consumer

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [Spring AMQP](https://spring.io/projects/spring-amqp)
- [RabbitMQ](https://www.rabbitmq.com/)
- [Docker](https://www.docker.com/)
