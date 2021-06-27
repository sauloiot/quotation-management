# QUOTATION MANAGEMENT API


## About project
  Is a REST based application which purpose is to store stock quotes from stock market.
 
 ## Links
 ### - [Postman containing all API requests](https://documenter.getpostman.com/view/5414747/Tzedi587)
  
 # Technologies and Tools Used
 - Java 11  
 - Spring Boot 2.4
 - IntelliJ IDEA
 - Postman
 - SmartGit
 - H2 databse
 - MySql
 
 # How to run 
 ### Requeriments:
 -Java 11, Spring boot 2.4 e Maven
 
 ```sh
git clone https://github.com/sauloiot/quotation-management.git 
cd quotation
mvnw spring-boot:run
```
 Or you can open the project in an IDE and run the file "QuotationApplication" in src/main/java/com/management/quotation/QuotationApplication.java.

 ### Generating project build
 The option generates the build if it is connected in the MySql 8 container and in the management api.
  ```sh
 mvnw clean package spring-boot:repackage
 java -jar target/web-store-0.0.1-SNAPSHOT.jar
```
Generating docker image.
  ```sh
 mvnw spring-boot:build-image
 docker run -p 8081:8081 quotation:0.0.1-SNAPSHOT
```

 # Author
 Saulo Ivo Oliveira Tavares
 
