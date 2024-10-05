# Video Game API AUtomation With Rest Assured: Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setting Up the Environment](#setting-up-the-environment)
   - [Clone the Repository](#clone-the-repository)
   - [Install Dependencies](#install-dependencies)
   - [Configuration](#configuration)
4. [Running Tests](#running-tests)
   - [Using Maven Command](#using-maven-command)
   - [Running Specific Test Classes](#running-specific-test-classes)
   - [Running Tests in IDE](#running-tests-in-ide)
5. [Viewing Reports](#viewing-reports)
   - [Default Reports Location](#default-reports-location)
   - [HTML Reports](#html-reports)
6. [Troubleshooting](#troubleshooting)
   - [Common Issues](#common-issues)
   - [Contact Support](#contact-support)

## 1. Introduction
The project is about Automation of the Video Game API using Rest Assured. The Swagger file can be found here https://www.videogamedb.uk/swagger-ui/index.html
This document provides instructions for setting up the testing environment, running the tests, and viewing the generated reports. This guide is intended for developers, QA engineers, and anyone involved in the testing process.

## 2. Prerequisites
Before you begin, ensure you have the following installed:
- **Java Development Kit (JDK)** 8 or higher
- **Apache Maven**
- **IDE** (e.g., IntelliJ IDEA, Eclipse) for viewing and editing code
- **Git** 

## 3. Setting Up the Environment

### Clone the Repository
1. Open your terminal or command prompt.
2. Clone the repository using Git:

  
   git clone https://github.com/razaqyaro/VideoGameAPI.git
   cd VideoGameAPI

   ### Install Dependencies

1.  Navigate to the project root directory.

2.  Run the following command to install all required dependencies:
    `mvn clean install`

### Configuration

Ensure the configuration properties are set correctly in `src/main/resources/config.properties`:

`base.url=https://www.videogamedb.uk:443`
`auth.endpoint=/api/authenticate`
`video.games.endpoint=/api/videogame`

4\. Running Tests
-----------------

To run the tests, you can use the following methods:

### Using Maven Command

1.  Open your terminal or command prompt.

2.  Navigate to the project root directory.

3.  Execute the tests using Maven:
    `mvn test`

### Running Specific Test Classes

If you want to run a specific test class, use the following command:
`mvn -Dtest=VideoGameTests test`

Replace `VideoGameTests` with the name of the test class you want to run.

### Running Tests in IDE

If you are using an IDE like IntelliJ IDEA or Eclipse:

1.  Open the test class you want to run.
2.  Right-click on the class name or method name.
3.  Select `Run 'VideoGameTests'`.

5\. Viewing Reports
-------------------

After running the tests, reports are generated automatically.

### Default Reports Location

The reports will be located in the `target/surefire-reports` directory. You can find various report files there.

### HTML Reports

To generate HTML reports, you can configure Extent Report, which has been set up in the project. After running the tests, you can find the HTML reports at
`reports/index.html`

Open this file in a web browser to view the detailed report.

6\. Troubleshooting
-------------------

### Common Issues

-   **Failed Tests:** If tests fail, check the logs in `target/surefire-reports` for more information on the failure.
-   **Dependencies Issues:** If you encounter dependency issues, ensure that your `pom.xml` is correctly set up and try running `mvn clean install` again.

### Contact Support

If you continue to experience issues, please reach out to the engineer at razaqyaro@gmail.com for further assistance.
