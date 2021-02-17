OpenWeather API
---
Open weather API Project

### Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database.

## Pre-requisites
a. Java 8
b. Maven

## Running the project
a. In root folder, do: <pre>mvn clean install</pre>
b. After successful build, execute <pre>mvn spring-boot:run</pre>
c. Server port configured: 8082

# Design Overview:

 1. Separation of concern has been used for designing the layered architecture.Given project was not having any particular design and all the classes were present under same package.
 2. All the classes with similar concerns have been grouped same package forming an layered architecture.
 3. All the classes have single responsibility and all the classes which had multiple responsibility for been separated accordingly.
 4. Interface has been created and can be segregated in future to add more functionality
 5. Inversion of control(DI) has been used so that all the layers are loosely coupled so that implementation classes can be easily changed in future if required & easy to write test cases.
 6. Unnecessary code has been removed which was not used in the application.

# Implementation Overview: 

Weather table has been created to store the required data. The DDL scripts for tables are available under src/main/resources.
H2 database has been used, can also check the data through console-  http://localhost:<port number>/h2-console
Connection properties are available in application.properties file.

API exposed:

     /weather?city=Amsterdam.
     This has been changed to GET API as the implementation of this API is to provide the weather report.
     Example : http://localhost:8082/weather?city=Amsterdam
     Request : Not Applicable
     Response: {
                "city": "Amsterdam",
                "country": "NL",
                "temperature": 280.56
               }
     