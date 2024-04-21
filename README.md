

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
7. [Endpoint Documentation](#endpoint-documentation)
8. [User Guide](#user-guide)
9. [Admin Guide](#admin-guide)
11. [Testing](#testing)
12. [Troubleshooting](#troubleshooting)
13. [Future Work](#future-work)

## Introduction

  
&nbsp; &nbsp;&nbsp; &nbsp;The Media Platform stands at the forefront of digital transformation in media consumption, sharing, and user interaction. Built on the powerful Java Spring framework and backed by the reliable MySQL database, it sets a new standard for user-centric media services. This web application is not just a content delivery system; it's a comprehensive ecosystem that supports dynamic user subscriptions, offers personalized content curation based on individual preferences, and promotes active user engagement through an intuitive review system. Moreover, its sophisticated recommendation engine utilizes advanced algorithms to suggest content, ensuring users always find something new and engaging to explore. By integrating these key functionalities, the Media Platform offers a seamless, enriching experience that connects users more closely to the content they love while providing administrators with robust tools for content management and user interaction analytics. This synergy of technology and user-focused design marks a significant leap forward in how digital media content is accessed and enjoyed, making it a pioneering solution in the media industry.

## System Overview

&nbsp; &nbsp;&nbsp; &nbsp;This platform distinguishes itself in the digital media landscape by offering an extensive suite of functionalities designed to enhance user engagement and streamline administrative oversight. Users are treated to a customized browsing experience, where content recommendations are meticulously curated to align with their individual tastes and viewing habits. This personalization is powered by sophisticated algorithms that analyze user interactions and preferences, ensuring that every recommendation is both relevant and engaging.

&nbsp; &nbsp;&nbsp; &nbsp;For administrators, the platform provides an array of robust tools that facilitate efficient content management and user moderation. Administrators can easily upload new content, update existing media, and categorize offerings to ensure easy navigation and discovery by users. Moreover, they have the ability to monitor user activity, manage subscriptions, and implement promotional strategies to enhance user retention and platform growth.

&nbsp; &nbsp;&nbsp; &nbsp;Additionally, the platform incorporates advanced security measures to protect user data and ensure a safe viewing environment, alongside analytics tools that offer deep insights into user behavior and content performance. This combination of features makes the platform not only a hub for high-quality digital media content but also a dynamic ecosystem where users and administrators alike can thrive.

&nbsp; &nbsp;&nbsp; &nbsp;By bridging the gap between content creators and consumers, the platform fosters a vibrant community where feedback and interaction shape the media landscape. This community-centric approach, coupled with the platform's innovative features, sets a new standard for digital media platforms, promising an unmatched user experience and streamlined content management.

&nbsp; &nbsp;&nbsp; &nbsp;The Observable design pattern plays a crucial role in the Media Platform, particularly in enhancing real-time interaction between the system and its users. This pattern is implemented within the Content Service to manage notifications whenever new content is added to the platform. Here, the ContentService acts as the Observable, maintaining a list of Observers, which in this context are users who have opted to receive updates.

&nbsp; &nbsp;&nbsp; &nbsp;When new content is successfully added to the database, the ContentService triggers a notification mechanism by invoking the notifyObservers method. This method iterates through the list of registered observers, which are instances of the User class that implement the Observer interface. Each observer has an update method, designed to handle incoming notifications about new content. This update process involves pushing relevant content details to the users, potentially leveraging user-specific criteria to tailor notifications.

&nbsp; &nbsp;&nbsp; &nbsp;This design not only decouples the content management operations from user notifications but also enhances the scalability of the notification system. It allows for the dynamic addition or removal of observers without modifying the core content management logic. By employing the Observable pattern, the Media Platform ensures that users remain engaged and informed about new offerings, thereby improving user experience and platform stickiness. This pattern is integral to creating an interactive, user-centric media environment where content discovery is seamless and highly responsive.

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

&nbsp; &nbsp;&nbsp; &nbsp;Ensure all software requirements are met before proceeding with the installation.

### Clone the Repository
git clone https://github.com/vladvadean/Media-Platform
cd yourprojectdirectory

### Install Dependencies
mvn install

### Set Up the Database
&nbsp; &nbsp;&nbsp; &nbsp;Create a new MySQL database and user, then run the provided SQL scripts to initialize the database schema.

### Configure Application Properties
&nbsp; &nbsp;&nbsp; &nbsp;Edit `src/main/resources/application.properties` to set your database credentials and other application settings.

### Run the Application

mvn spring-boot:run

&nbsp; &nbsp;&nbsp; &nbsp;The application should now be accessible on `http://localhost:8080`.

## Endpoint Documentation

This section provides detailed documentation of all API endpoints associated with each service class. It includes the HTTP methods, path URLs, expected input, and output formats.

### Admin Endpoints

#### Get All Admins
- **GET** `/admin/all`
- **Description**: Retrieves a list of all admins.
- **Response**: `200 OK` with JSON array of admins.

#### Get Admin by ID
- **GET** `/admin/find/{id}`
- **Description**: Retrieves a specific admin by their ID.
- **Path Variable**: `id` - The ID of the admin.
- **Response**: `200 OK` with admin details in JSON.

#### Add Admin
- **POST** `/admin/add`
- **Description**: Adds a new admin.
- **Request Body**: JSON object containing admin details.
- **Response**: `201 Created` with created admin details in JSON.

#### Update Admin
- **PUT** `/admin/update`
- **Description**: Updates an existing admin.
- **Path Variable**: `id` - The ID of the admin.
- **Request Body**: JSON object with updated admin data.
- **Response**: `200 OK` with updated admin details in JSON.

#### Delete Admin
- **DELETE** `/admin/delete{id}`
- **Description**: Deletes an admin by ID.
- **Path Variable**: `id` - The ID of the admin to delete.
- **Response**: `204 No Content`

#### Get All Content
- **GET** `/admin/getContent/{adminId}`
- **Description**: Get all the content inserted by the admin with adminId.
- **Path Variable**: `adminId` - The ID of the admin to find the content.
- **Response**: `200 OK`

### Content Endpoints

#### Get All Content
- **GET** `/content/all`
- **Description**: Retrieves all content items.
- **Response**: `200 OK` with JSON array of content items.

#### Get Content by ID
- **GET** `/content/find/{id}`
- **Description**: Retrieves a specific content item by its ID.
- **Path Variable**: `id` - The ID of the content item.
- **Response**: `200 OK` with content details in JSON.

#### Add Content
- **POST** `/content/add`
- **Description**: Adds a new content item.
- **Request Body**: JSON object containing content details.
- **Response**: `201 Created` with created content details in JSON.

#### Update Content
- **PUT** `/content/update`
- **Description**: Updates an existing content item.
- **Path Variable**: `id` - The ID of the content item to update.
- **Request Body**: JSON object with updated content data.
- **Response**: `200 OK` with updated content details in JSON.

#### Delete Content
- **DELETE** `/content/delete/{id}`
- **Description**: Deletes a content item by its ID.
- **Path Variable**: `id` - The ID of the content to delete.
- **Response**: `204 No Content`

#### Get User That Liked Content
- **GET** `/content/getAllUsersThatLiked/{contentId}`
- **Description**: Gets all the users that liked the content with contentId.
- **Path Variable**: `contentId` - The ID of the content to get users.
- **Response**: `200 OK`

### Billing Details Endpoints

#### Get Billing Details by ID
- **GET** `/billingDetails/find/{id}`
- **Description**: Retrieves billing details with a specific id.
- **Path Variable**: `id` - The ID of the billing details.
- **Response**: `200 OK` with billing details in JSON.

#### Get All Billing Details
- **GET** `/billingDetails/all`
- **Description**: Retrieves all billing records.
- **Response**: `200 OK` with JSON array of billing records.

#### Add Billing Details
- **POST** `/billingDetails/add`
- **Description**: Adds new billing details.
- **Request Body**: JSON object containing billing details.
- **Response**: `201 Created` with created billing details in JSON.

#### Update Billing Details
- **PUT** `/billingDetails/update`
- **Description**: Updates existing billing details.
- **Request Body**: JSON object with updated billing details.
- **Response**: `200 OK` with updated billing details in JSON.

#### Delete Billing Details
- **DELETE** `/billingDetails/delete/{id}`
- **Description**: Deletes billing details by ID.
- **Path Variable**: `id` - The ID of the billing details to delete.
- **Response**: `204 No Content`

### User Endpoints
#### Get All Users
  - **GET** `/user/all`
  - **Description**: Retrieves all users.
  - **Response**: `200 OK` with JSON array of users.

#### Get User by Id
  - **GET** `/user/find/{id}`
  - **Description**: Retrieves a specific user by their ID.
  - **Path Variable**: `id` - The ID of the user to be found.
  - **Response**: `200 OK` with user details in JSON.

#### Add user
  - **POST** `/user/add`
  - **Description**: Creates a new user.
  - **Request Body**: JSON object containing user details.
  - **Response**: `201 Created` with created user details in JSON.

#### Update user
- **PUT** `/user/update`
- **Description**: Updates an existing user.
- **Request Body**: JSON object with updated user data.
- **Response**: `200 OK` with updated user details in JSON.

#### Delete user
  - **DELETE** `/user/delete/{id}`
  - **Description**: Deletes a user by ID.
  - **Path Variable**: `id` - The ID of the user to be deleted.
  - **Response**: `204 No Content`

#### Get LastPayment of User by ID
  - **GET** `/user/getLastPayment/{userId}`
  - **Description**: Retrieves the billing details of the last payment of a user with a certain id.
  - **Path Variable**: `userId` - The ID of the user to be found.
  - **Response**: `200 OK` with user details in JSON.

#### Get All LikedContent by User by ID
  - **GET** `/user/getAllLikedContent/{userId}`
  - **Description**: Retrieves all the liked content of a user with a certain id.
  - **Path Variable**: `userId` - The ID of the user to be found.
  - **Response**: `200 OK` with user details in JSON.

### Liked Content Endpoints
#### Get All LikedContent
  - **GET** `/likedContent/all`
  - **Description**: Retrieves all liked content records.
  - **Response**: `200 OK` with JSON array of liked content records.

#### Get LikedContent by ID
  - **GET** `/likedContent/find/{id}`
  - **Description**: Retrieves a specific liked content record by its ID.
  - **Path Variable**: `id` - The ID of the likedContent to be found.
  - **Response**: `200 OK` with liked content details in JSON.

#### Add LikedContent
  - **POST** `/likedContent/add`
  - **Description**: Creates a new liked content record.
  - **Request Body**: JSON object containing liked content details.
  - **Response**: `201 Created` with created liked content details in JSON.

#### Delete LikedContent
  - **DELETE** `/likedContent/delete/{id}`
  - **Description**: Deletes a liked content record by ID.
  - **Path Variable**: `id` - The ID of the user to be deleted.
  - **Response**: `204 No Content`

#### Get LikedContent by User ID and Content ID
  - **GET** `likedContent/findUserContent/{userId}/{contentId}`
  - **Description**: Retrieves all liked content records.
  - **Path Variable**: 
  - - `contentId` - The ID of the content to be found.
  - - `userId` - The ID of the user to be found.
  - **Response**: `200 OK` with JSON array of liked content records.

## Architecture

### Application Layers
- **Controller Layer**: Manages HTTP request/response cycles.
- **Service Layer**: Implements the business logic.
- **Repository Layer**: Handles data persistence operations.
- **Security Layer**: Ensures authenticated and authorized access.

### Controller Layer
&nbsp; &nbsp;&nbsp; &nbsp;The Controller Layer serves as the gateway for HTTP requests, effectively bridging user interactions with the application's functionalities. It interprets user inputs captured through forms or URLs and delegates them to the appropriate service layer methods. Essential for validating incoming data, this layer orchestrates the application logic flow based on user requests and determines the nature of responses (HTML, JSON, etc.) to be returned. As the intermediary between the user interface and application's core operations, the Controller Layer ensures a smooth and coherent user experience by managing the HTTP request/response cycle.

### Service Layer
&nbsp; &nbsp;&nbsp; &nbsp;At the heart of the application, the Service Layer is where business logic is centralized, orchestrating data flow to and from the repository layer and preparing it for the controller layer. It hosts service classes responsible for executing specific business tasks like subscription fee calculations, user authentication, or content recommendation algorithms. This layer is pivotal for managing transactions, guaranteeing that business processes are executed accurately and efficiently. By abstracting the complexity of the business operations, the Service Layer promotes maintainability and scalability through a well-defined separation of concerns.

### Repository Layer
&nbsp; &nbsp;&nbsp; &nbsp;The Repository Layer is the application's data access gateway, abstracting the complexities of interactions with the data source. It provides a collection of operations for CRUD actions, allowing other application parts to interact with the database seamlessly without direct query handling. This layer plays a vital role in ensuring data integrity and consistency, managing entity relationships, and encapsulating database transactions. It effectively isolates data access logic, facilitating straightforward data operations and contributing to the application's overall clean architecture.

### Security Layer
&nbsp; &nbsp;&nbsp; &nbsp;Protecting the application from unauthorized access and potential security threats is the primary role of the Security Layer. It enforces authentication and authorization protocols, ensuring users are accurately identified and granted access based on their roles. This layer manages user sessions, encrypts sensitive data, and safeguards against common vulnerabilities (SQL injection, XSS, CSRF). Moreover, it upholds security policies, controlling password standards and user access rights, crucial for preserving data integrity and user privacy. As the defender of application security, this layer is indispensable for establishing a trusted and secure user environment.

### Database Schema
&nbsp; &nbsp;&nbsp; &nbsp;The schema includes `User`, `Content`, `Admin`, `BillingDetails`, and `LikedContent` tables, designed to support efficient data management and retrieval.

![p1](https://github.com/vladvadean/Media-Platform/assets/126804850/e722c929-ef13-4977-829f-73e09eb630c7)

## User Guide
&nbsp; &nbsp;&nbsp; &nbsp;The user is the most important actor in the whole project. The user has the following use cases:

 - login the platform
 - create a new account
 - buy/cancel a subscription
 - watch content
 - leave review
 - watch the list of liked content
 - get information about his payments
 - get notified on new content added
 
&nbsp; &nbsp;&nbsp; &nbsp;The user has the following attributes:
 - email
 - password
 - username
 - id
 - lastPaymentDate(the last date his payment was received)
 
&nbsp; &nbsp;&nbsp; &nbsp; The authentication will be made only with the email and password, the username will be used to display its online persona. Last payment date attribute will be used to control the validity of the user, him being able to access any of the content.

## Admin Guide
&nbsp; &nbsp;&nbsp; &nbsp; The admin is the actor with the most powerful features on the platform. The admin use cases are:

 - delete a user
 - add/remove content
 - check the payment history of a user
 - check the statistics of any content
 - add/delete another admin
 
 &nbsp; &nbsp;&nbsp; &nbsp;The admins attributes are:
 
 - id
 - name
 - password
 - username

  &nbsp; &nbsp;&nbsp; &nbsp;The password and the name are required for the admin to login. Again the username will only will be used to display his digital persona. In case any user has malicious intents he will be deleted. Any other problem such as the payment of the subscription to be reevaluated manually should be a task for the admin. Having such power an admin should be the only one that can add another admins and the only to remove or add content.

## Testing
 &nbsp; &nbsp;&nbsp; &nbsp;The testing of the endpoints for the backend functionality Postman is the tool used to test the program in it's development. Every endpoint and every of its scenarios was tested in postman.

## Troubleshooting
 &nbsp; &nbsp;&nbsp; &nbsp;Most common issues:

 - localhost:8080 does not work, but on other port does.
Probably the port 8080 is already used by another process or that the properties of the project are not correctly set. Check the application properties one more time and terminate the process that is using the 8080 port, or use another port

## Future Work
_to be added_
