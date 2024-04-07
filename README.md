# Media Platform Development Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [System Overview](#system-overview)
3. [Software Requirements](#software-requirements)
4. [Hardware Requirements](#hardware-requirements)
5. [Installation Guide](#installation-guide)
6. [Architecture](#architecture)
   - [Application Layers](#application-layers)
   - [Database Schema](#database-schema)
7. [API Documentation](#api-documentation)
8. [User Guide](#user-guide)
9. [Admin Guide](#admin-guide)
10. [Security](#security)
11. [Testing](#testing)
12. [Troubleshooting](#troubleshooting)
13. [Future Work](#future-work)
14. [Contributors](#contributors)

## Introduction

The Media Platform is an innovative web application designed to modernize the consumption, sharing, and interaction with digital media content. Leveraging the robust capabilities of Java Spring and MySQL, this platform introduces features essential for managing subscriptions, delivering personalized content, and facilitating user engagement through reviews and interactive recommendations.

## System Overview

This platform uniquely positions itself in the digital media market by providing a comprehensive suite of tools for users and administrators. Users enjoy a tailored experience with content recommendations based on their preferences, while administrators have powerful tools at their disposal for content management and user oversight.

## Software Requirements

- **Java Development Kit (JDK)**: Version 21
- **Spring Boot**: Latest stable version for backend development.
- **MySQL Server**: Version 8.0 or above for database management.
- **Maven**: Dependency management and project build.
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE.

## Hardware Requirements

- **Processor**: Dual-core minimum, Quad-core recommended.
- **RAM**: 4 GB minimum, 8 GB recommended.
- **Disk Space**: At least 20 GB of free space.
- **Network**: Stable internet connection.

## Installation Guide

Ensure all software requirements are met before proceeding with the installation.

### Clone the Repository

git clone https://yourrepository.git](https://github.com/vladvadean/Media-Platform
cd yourprojectdirectory

### Install Dependencies

mvn install


### Set Up the Database
Create a new MySQL database and user, then run the provided SQL scripts to initialize the database schema.

### Configure Application Properties
Edit `src/main/resources/application.properties` to set your database credentials and other application settings.

### Run the Application

mvn spring-boot:run

The application should now be accessible on `http://localhost:8080`.

## Architecture

### Application Layers
- **Controller Layer**: Manages HTTP request/response cycles.
- **Service Layer**: Implements the business logic.
- **Repository Layer**: Handles data persistence operations.
- **Security Layer**: Ensures authenticated and authorized access.

### Database Schema
The schema includes `Users`, `Content`, `Subscriptions`, and `BillingDetails` tables, among others, designed to support efficient data management and retrieval.
![P1DatabaseDiagram](https://github.com/vladvadean/Media-Platform/assets/126804850/9fe85114-b5e6-4248-bb25-ae4117fd4f72)

## API Documentation
Details the RESTful endpoints, including paths, supported HTTP methods, request parameters, and response models. Use tools like Swagger for automatic documentation and testing.

## User Guide
Explains how users can register, subscribe, browse, and interact with content, including leaving reviews and accessing personalized recommendations.

## Admin Guide
Covers content management, user moderation, subscription overview, and analytics tools available to administrators for platform oversight.

## Security
Describes the security measures implemented, such as HTTPS, password hashing, JWT for authentication, and role-based access control.

## Testing
Outlines the testing strategy, including unit tests for repositories, service layer integration tests, and end-to-end tests using tools like Selenium.

## Troubleshooting
Provides solutions to common issues, such as database connection errors, missing dependencies, and application deployment problems.

## Future Work
Discusses planned features and improvements, fostering community engagement and technological advancement.

## Contributors
Lists the individuals and teams who have contributed to the project, acknowledging their efforts and expertise.
