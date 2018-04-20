This is a microservices demo project, please follow below steps to access them.  If you use docker, please see the "ReadyMe for Docker" section.

1. make sure you have MySql installed
2. build this project by command "mvn package"
3. start it by "java -jar microservice.product.service-0.0.1-SNAPSHOT.jar > 1.log" under your project's target folder
4. check the log in 1.log that in target folder
5. run data.sql that in the project's resources folder
6. access http://localhost:8002/product/1 to see the result

## ReadyMe for Docker
Precondition: Install docker and docker compose in your local

### Start a specific microservice
open terminal(cmd), navigate to the related folder and run below commands:
Take microservice.product.service as example:

#### build image
   cd microservice.product.service

   mvnw package dockerfile:build

#### start service
   docker-compose up -d

Then access http://localhost:8002/product/1 to see the result

You can stop the service via command - docker-compose down

### Start all the microservices
1. Go to each microservice, and run mvnw package dockerfile:build
2. Go back to parent folder ecommercemicroservice, then type command -  docker-compose up -d
3. Check the registered service on Eureka via http://localhost:8761