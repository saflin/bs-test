### Solution for Black Swan Technical Test
Implements 

* Rest endpoint for create/update/list/listall users

* Rest endpoint for create/update/list/delete task for users

* Schedule job to update status of old pending tasks.


### Building the solution

 Requires jdk 1.8 to build the solution.
 
 Run the below command to build the solution
 ```
  ./gradlew clean  build  --info 
 ```  
  
### Running the solution

Run the below command to bring up Spring boot app.
 ```
  ./gradlew bs-test-service:bootRun --info
 ``` 

### Embedded H2 database
The solution uses H2 in memory database for persistence.you can query the database in your browser and using url jdbc:h2:mem:testdb
user name and password is sa

```
http://localhost:8080/h2
```

### Scheduler job configuration

A cron job has been configured to run every 30 secs. cron expresion can changed in application properties.

### Swagger ui
http://localhost:8080/swagger-ui.html


### Swagger API Docs Json
http://localhost:8080/v2/api-docs


### health endpoint
http://localhost:8080/health


### metrics endoint
http://localhost:8080/metrics


### Improvements

* Solution has zero test coverage. Should write unit and components.

* Add more logs for debugging purpose. there is limited logs at the moment.

* Create user api should check whether user already exists to avoid duplicate user

* Create task api should check for ducplicate task before creating new task for user

* Get all users api should use pagination.