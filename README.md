# Train Ticketing & Management System - Technical Assessment

A Java-based desktop application developed to manage train scheduling, routing logic (direct and indirect), and passenger reservations. The project focuses on modular architecture, data integrity, and separation of concerns.

## 1. Key Functionalities

* **Routing Algorithm:** Supports both direct trips and indirect connections (changeovers). If no direct train exists between two stations, the system identifies intermediate stations to link the journey.
* **Booking System:** Implements a strict seat availability check to prevent overbooking. It validates requested seats against the real-time capacity fetched from the database.
* **Admin Dashboard:** Provides tools for system maintenance, including train management (CRUD operations), route creation, and real-time delay updates.
* **Notification Flow:** Automated notification system for booking confirmations and delay alerts. Currently implemented via a `MockEmailProvider` that logs communications to a local audit file (`mail_server.log`).

## 2. Technical Architecture

The application is built using a **Layered Architecture** to ensure maintainability and testability:

* **Model Layer:** Contains POJOs (Plain Old Java Objects) representing the database entities (`Train`, `Route`, `Station`, `Booking`).
* **DAO Layer (Data Access Object):** Encapsulates all SQL interactions using JDBC. Each DAO is responsible for a specific entity, ensuring a clean separation from the business logic.
* **Service Layer:** Centralizes business rules. The `BookingService` and `EmailService` act as orchestrators between the Controllers and the DAOs, ensuring that logic (like overbooking checks) is not leaked into the UI.
* **Controller Layer:** Manages user input, navigation, and view updates.
* **View Layer:** Built with Java Swing, following a modular approach where each screen is a separate component.

## 3. Design Decisions

* **Interface-based Notification System:** By using an `EmailProvider` interface, the notification logic is decoupled from the implementation. This allowed the use of a `MockEmailProvider` for this assessment, ensuring security (no hardcoded credentials) while remaining "plug-and-play" for real SMTP integration.
* **Database-Driven Integrity:** I relied on PostgreSQL foreign keys and constraints to handle data consistency at the storage level, rather than relying solely on application-level checks.
* **Single Source of Truth:** Train capacity and seat availability are fetched directly from the database for every booking attempt to prevent issues with stale data in multi-user scenarios.

## 4. Tech Stack

* **Language:** Java 17
* **GUI:** Java Swing
* **Database:** PostgreSQL 15+
* **Drivers:** JDBC (PostgreSQL Driver)

## 5. Database Schema

The system relies on five primary tables:
1.  `stations`: List of all physical train stops.
2.  `routes`: Logical paths connecting stations.
3.  `route_stations`: Junction table defining the sequence and schedule of stops.
4.  `trains`: Individual train units assigned to routes.
5.  `bookings`: Records of passenger reservations.

## 6. Setup & Installation

1.  **Database:** Execute the `database_setup.sql` script provided in the root folder to initialize the schema and populate sample data.
2.  **Configuration:** Update the database URL, username, and password in `src/DAO/DatabaseConnection.java`.
3.  **Run:** Launch the application through the `App.java` main class.
