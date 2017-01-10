# PA165-project
Rental of construction machinery project for PA165 course at FI MUNI.

## REST API

### Requirements
Maven and JDK versions required to test project

```
Apache Maven 3.13.0
Java version: 1.8.0_111, vendor: Oracle Corporation
```

### Running the REST
After compilation of the project via the commands specified in Milestone 3 you can run the REST API with these commands:

```
$ cd rest
$ mvn tomcat7:run
```

### Using the REST
#### Get all Machines
```
$ curl -i -X GET http://localhost:8080/rest/machines
```

#### Get specific machine
```
$ curl -i -X GET http://localhost:8080/rest/machines/1
```

#### Delete machine
```
$ curl -i -X DELETE http://localhost:8080/rest/machines/1
```

#### Create a new machine
```
$ curl -X POST -i -H "Content-Type: application/json" --data '{"name":"test", "dateOfBuy":"2016-12-12","machineType":"CRANE","pricePerDay":"200", "dateOfLastRevision":"2016-12-12"}' http://localhost:8080/rest/machines/create
```
