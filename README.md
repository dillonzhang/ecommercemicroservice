This is a microservices demo project, please follow below steps to access them.  If you use docker, please see the "ReadyMe for Docker" section.

1. make sure you have MySql and Redis installed
2. build the projects by command "mvn clean package"
3. start by right click the Application.java and select run as java application
4. check the application.yml and prepare database account 
5. run data.sql that in the project's resources folder if needs
6. start those common microservice follow this order: Eureka, Zuul, Auth
7. start business microservice like Product service and check API from Swagger localhost:8102/swagger-ui.html
8. those API needs Oauth Token to access, current solution support Client Credentials and Resource Owner Password Credentials, details in following steps
9. 密码模式get token
![](https://github.com/dillonzhang/ecommercemicroservice/blob/develop/img-folder/gettoken.PNG)
10. 客户端模式 get token 
![](https://github.com/dillonzhang/ecommercemicroservice/blob/develop/img-folder/clientgettoken.PNG)
11. use token 
![](https://github.com/dillonzhang/ecommercemicroservice/blob/develop/img-folder/usetoken.PNG)
12. login endpoint 
![](https://github.com/dillonzhang/ecommercemicroservice/blob/develop/img-folder/logintoken.PNG)

Note that: refresh token for 密码模式还有bug，暂时用不了

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