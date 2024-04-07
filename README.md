
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
8. [User Guide](#user-guide)
9. [Admin Guide](#admin-guide)
11. [Testing](#testing)
12. [Troubleshooting](#troubleshooting)
13. [Future Work](#future-work)

## Introduction

  
&nbsp; &nbsp;&nbsp; &nbsp;The Media Platform stands at the forefront of digital transformation in media consumption, sharing, and user interaction. Built on the powerful Java Spring framework and backed by the reliable MySQL database, it sets a new standard for user-centric media services. This web application is not just a content delivery system; it's a comprehensive ecosystem that supports dynamic user subscriptions, offers personalized content curation based on individual preferences, and promotes active user engagement through an intuitive review system. Moreover, its sophisticated recommendation engine utilizes advanced algorithms to suggest content, ensuring users always find something new and engaging to explore. By integrating these key functionalities, the Media Platform offers a seamless, enriching experience that connects users more closely to the content they love while providing administrators with robust tools for content management and user interaction analytics. This synergy of technology and user-focused design marks a significant leap forward in how digital media content is accessed and enjoyed, making it a pioneering solution in the media industry.

## System Overview

&nbsp; &nbsp;&nbsp; &nbsp;This platform uniquely positions itself in the digital media market by providing a comprehensive suite of tools for users and administrators. Users enjoy a tailored experience with content recommendations based on their preferences, while administrators have powerful tools at their disposal for content management and user oversight.

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

## Architecture

### Application Layers
- **Controller Layer**: Manages HTTP request/response cycles.
- **Service Layer**: Implements the business logic.
- **Repository Layer**: Handles data persistence operations.
- **Security Layer**: Ensures authenticated and authorized access.

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
