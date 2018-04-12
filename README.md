this is a microservices demo project, please follow below steps to access them. 

1. make sure you have MySql installed
2. build this project by command "mvn package"
3. start it by "java -jar microservice.product.service-0.0.1-SNAPSHOT.jar > 1.log" under your project's target folder
4. check the log in 1.log that in target folder
5. run data.sql that in the project's resources folder
6. access http://localhost:8002/product/1 to see the result