# Trails Booking Application

## Built With
* Java 11
* Spring Boot Framework
* Maven - Build tool
* h2 - In-memory database

### Other libraries used
* Junit5 - For testing
* Mockito - For mocking in tests
* Lombok - For logging, auto code generation


### How to build and start locally
 Using docker
```bash
$ mvn clean isntall
$ docker build -t trails-booking-app . 
$ docker run -p 8080:8080 trails-booking-app
```
OR

 Using jar
```bash
$ mvn clean isntall
$ java -jar target/trails-booking-app-0.0.1-SNAPSHOT.jar

```

### Available Endpoints
This api doesn't have a Swagger/OpenAPI integration yet.
Thus the available endpoints information has been added here.

#### Booking Endpoints
There is no authentication or authorization implemented now on the requests. Ideally these requests should be allowed for a specific role. 
Like only guide or hiker who created the booking should be allowed to delete the booking.

#### POST /api/bookings - To create booking.
Example: 
```console
curl -H "Content-type: application/json" -X POST http://localhost:8080/api/bookings -d '{"trailId": 1, "hikerDTOs" : [ {"name" : "kumudini", "age" : 20 },{"name" : "harsh" , "age" : 15 }],"hikeDate": "2020-06-06"}'
```
Description : Creates a new booking. 
This api will validate dto for valid trail-id, hike date and if the age of the hikers lie in the given min and max range of the trail.
Every booking creation request, creates new hiker entities. This can be extended later if app needs to store unique hiker data. 

#### GET /api/bookings?date - To view bookings for a specified date
Example
```console
curl -X GET http://localhost:8080/api/bookings?date=2020-06-06
```
Description: Gets all bookings which has hike date as given date in request param. 
This accepts date in format 'yyyy-mm-dd' otherwise throws Bad request exception. 

#### DELETE /api/bookings/1 - To cancel the booking made. 
```console
curl -X DELETE http://localhost:8080/api/bookings/1
```
Description: Deletes the booking made of given id and associated hiker entities as well. 
This api does hard delete in database for now. This ideally could be handled with isActive flag on database level as well. 
This validates if booking is present or not. 

#### GET /api/hikers/{id}/bookings - To view booking details of the hiker. 
```console
curl -X GET http://localhost:8080/api/hikers/1/bookings
```
Description : Gets booking details of this hiker. 


### Production readiness

Ideally application shall follow 12 factor app. (https://12factor.net/) to make sure that its ready for the production. 

For the sake of the assignment, as with the limited time I have kept in mind following to make sure app is ready for production
1. Application is dockerized. 
2. Requests are logged extensively, this later can be used in streams. 
3. Unit and integration tests are written. 