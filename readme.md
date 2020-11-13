# TRAIN-ENQUIRY

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) 

## Requirements

For building and running the application you need:

- [JDK 11](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven ](https://maven.apache.org)

## Running the application locally
   
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.gatewaygroup.trainenquiry.TrainEnquiryApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Endpoint
````
Method: GET
URI: localhost:8080/gatewaygroup/api/trains
REQUIRED HEADERS: SHIRO_SECURITY_USERNAME = gatewaygroup
                  SHIRO_SECURITY_PASSWORD = trains@123
SAMPLE RESPONSE: {
                     "headers": {},
                     "body": {
                         "trainDetails": {
                             "trainNumber": 3,
                             "departureDate": "2020-08-25",
                             "timestamp": "2020-08-25T09:59:18.000Z",
                             "location": {
                                 "type": "Point",
                                 "coordinates": [
                                     28.823561,
                                     61.217463
                                 ]
                             },
                             "speed": 74
                         },
                         "currentLocation": "Ratavallinkaari 9, 55800 Imatra, Finland"
                     },
                     "statusCode": "OK",
                     "statusCodeValue": 200
                 }
````

## Postman collection
src/test/resources/postman/train-enquiry.postman_collection.json
