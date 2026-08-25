# Courier & Logistic Tracking System

## 📌 About the Project

**Courier & Logistic Tracking System** is a Spring Boot REST API project built to manage the complete courier and shipment lifecycle. It brings customers, shipments, packages, payments, warehouses, delivery agents, and tracking history together in one application.

The project focuses on keeping courier operations simple, organized, and easy to manage through REST APIs.

## ✨ Features

- Customer management
- Shipment creation and management
- Package management
- Payment and payment-status management
- Warehouse management
- Delivery agent management
- Shipment status updates
- Delivery agent assignment
- Warehouse assignment
- Shipment tracking history
- Search shipment by tracking number
- Filter shipments by customer, warehouse, delivery agent, source, destination, and delivery date
- Pagination and sorting
- Request and response DTOs
- Input validation
- JPA entity relationships

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- ModelMapper
- Bean Validation
- REST APIs

## 🏗️ Project Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Service Implementation
    ↓
Repository
    ↓
Database
```

### Project Structure

```text
src/main/java/com/courier/tracking/system/

├── controller
├── entity
├── repository
├── requestdto
├── responsedto
├── service
└── service/impl
```

## 📦 Main Modules

### Customer
Manages customer information such as name, email, and contact details.

### Shipment
The main module of the application. It manages shipment details, tracking numbers, shipment status, delivery dates, warehouse assignment, and delivery agent assignment.

### Package
Stores package-related information and package types associated with shipments.

### Payment
Manages payment amount, payment method, payment status, and payment date/time.

### Warehouse
Manages warehouse details such as name, location, capacity, and contact information.

### Delivery Agent
Manages delivery agents, vehicle information, ratings, and availability status.

### Tracking History
Maintains shipment tracking information and allows tracking history to be retrieved using a shipment tracking number.

## 🔗 Entity Relationships

```text
Customer
   │
   └── One-to-Many ──> Shipment

Warehouse
   │
   └── One-to-Many ──> Shipment

DeliveryAgent
   │
   └── One-to-Many ──> Shipment

Shipment
   ├── One-to-One ──> Package
   ├── One-to-One ──> Payment
   └── One-to-Many ──> TrackingHistory
```

## 🔄 Shipment Creation Flow

When a shipment is created, the system can associate:

1. Shipment details
2. Package details
3. Payment details
4. Initial tracking history

These objects are connected through JPA relationships and persisted as part of the shipment creation workflow.

## 🔎 Search, Filtering, Pagination & Sorting

Shipments can be searched or filtered by:

- Shipment ID
- Tracking number
- Customer
- Warehouse
- Delivery agent
- Source and destination
- Delivery date

The shipment API also supports **pagination with sorting** for handling shipment records more efficiently.

## 📄 DTO & Validation

The project uses separate **Request DTOs** and **Response DTOs** instead of directly exposing entities through APIs.

```text
Request DTO
     ↓
Controller
     ↓
Service
     ↓
Entity
     ↓
Repository
     ↓
Database
```

**ModelMapper** is used to simplify conversion between DTOs and entities.

Bean Validation is used to validate incoming data such as required fields, contact numbers, weight, package information, and warehouse capacity.

## ⚙️ Configuration

Update the database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/courier_tracking
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Replace the database credentials with your own MySQL credentials. Do not commit real passwords or sensitive information to GitHub.

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project in Eclipse, IntelliJ IDEA, or Spring Tool Suite.

### 3. Configure MySQL

Create the required database and update the credentials in `application.properties`.

### 4. Run the application

Run the main Spring Boot application class.

The application will start on:

```text
http://localhost:8080
```

## 🧪 API Testing

The REST APIs can be tested using:

- Postman

## 🎯 What I Worked On

Through this project, I worked with:

- REST API development using Spring Boot
- Layered backend architecture
- Spring Data JPA and Hibernate
- Entity relationships and database operations
- Request and Response DTOs
- Bean Validation
- ModelMapper
- Shipment business workflows
- Status and assignment operations
- Filtering, pagination, and sorting

## 🚀 Future Improvements

- Spring Security and JWT authentication
- Role-based access control
- Global exception handling
- Swagger/OpenAPI documentation
- Email/SMS shipment notifications
- Automated unit and integration testing
- Docker support
- Cloud deployment

## 👨‍💻 Author

**Ranjan Kumar**

Java | Spring Boot | REST APIs | JPA | MySQL
