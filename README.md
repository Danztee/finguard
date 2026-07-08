 # FinGuard

FinGuard is a robust, production-ready microservices ecosystem built with Java 17 and the Spring Boot 3 framework. The project demonstrates modern distributed system patterns, including service discovery, centralized configuration, event-driven communication, and container orchestration.

## 🏗 Architecture

The system consists of several specialized services and infrastructure components:

### Core Services
- **Customer Service**: Manages customer registrations and profiles.
- **Fraud Service**: Performs real-time fraud checks on customer activities.
- **Notification Service**: Handles asynchronous user alerts via RabbitMQ.
- **Clients**: A shared module containing Feign clients and common DTOs for inter-service communication.
- **AMQP**: Shared module for RabbitMQ configuration and message publishing logic.

### Infrastructure
- **API Gateway**: Centralized entry point for all requests, handling routing and security.
- **Eureka Server**: Service discovery and registration.
- **PostgreSQL**: Relational database for persistent storage.
- **RabbitMQ**: Message broker for event-driven, asynchronous processing.
- **Zipkin**: Distributed tracing for monitoring and troubleshooting request flows.
- **pgAdmin**: Web-based administration interface for PostgreSQL.

## 🛠 Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3, Spring Cloud
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Messaging**: RabbitMQ
- **Tracing**: Spring Cloud Sleuth / Zipkin
- **Containerization**: Docker, Jib
- **Orchestration**: Kubernetes (Minikube)

## 🚀 Getting Started

### Prerequisites
- Docker & Docker Compose
- Java 17 (if running locally)
- Maven (if building from source)
- Minikube (for Kubernetes deployment)

### Running with Docker Compose
The easiest way to spin up the entire environment (including infrastructure) is using Docker Compose:

```bash
docker-compose up -d
```

Once running, you can access:
- **Eureka Dashboard**: `http://localhost:8761`
- **Zipkin UI**: `http://localhost:9411`
- **pgAdmin**: `http://localhost:5050` (Login with `admin@admin.com` / `admin`)
- **RabbitMQ Management**: `http://localhost:15672` (Login with `guest` / `guest`)

### Deploying to Kubernetes (Minikube)
Kubernetes manifests are located in the `k8s/minikube` directory. To deploy:

1. Start Minikube:
   ```bash
   minikube start
   ```
2. Apply bootstrap configurations (Postgres, Zipkin, etc.):
   ```bash
   kubectl apply -f k8s/minikube/bootstrap/postgres
   kubectl apply -f k8s/minikube/bootstrap/zipkin
   ```
3. Apply service deployments:
   ```bash
   kubectl apply -f k8s/minikube/services/customer
   kubectl apply -f k8s/minikube/services/fraud
   kubectl apply -f k8s/minikube/services/notification
   ```

## 📡 API Endpoints

### Customer Service
- `POST /api/v1/customers`: Register a new customer.
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }
  ```

### Fraud Service
- `GET /api/v1/fraud-check/{customerId}`: Check if a specific customer is flagged as a fraudster.

---
Developed by [danztee](https://github.com/danztee)
