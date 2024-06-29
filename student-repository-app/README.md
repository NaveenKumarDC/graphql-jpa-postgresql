# Spring boot application with Graphql and JPA (Postgres15)

# Components Used

    - Spring
      - Spring boot 3+
      - Spring Graphql
      - Spring JPA + Posgtresql 15
      - Java
        - Java 17+

### Resources

    - GraphQL Spec : https://spec.graphql.org/draft/#sec-Overview
    - Spring Graphql Guides : https://spring.io/guides/gs/graphql-server
    - Spring JPA : 

### Pre-requisite
- Install local postgresql 15
    - Create a database
        - Set USER_NAME and PASSWORD
        - Grant DDL creation permission
- Installing Git
- Download altair client - Optional for testing Graphql Endpoints


### Why ?
 - To learn how to create Spring boot application with Graphql integration 

### Functionality
- Creating a Student Details with Address and List of subjects student enrolled.
- Accessing using the graphql end points
    - Query Student
    - Add Student



### How ?
- Clone the Repository
- Setup local Postgresql15
- Update application.properties file with below properties
    - ##### PostgreSQL
      ````
      spring.datasource.url=jdbc:postgresql://localhost:5432/<DATA_BASE_NAME>
      spring.datasource.username=<USER_NAME>
      spring.datasource.password=<PASSWORD>
      ````
- Run spring boot application
    - Verify if Graphiql client started by checking the console logs
        - 2024-06-29T05:23:10.231+05:30  INFO 11879 --- [student-repository] [           main] s.b.a.g.s.GraphQlWebMvcAutoConfiguration : GraphQL endpoint HTTP POST /graphql
          2024-06-29T05:23:10.404+05:30  INFO 11879 --- [student-repository] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8093 (http) with context path '/'
- Go to web browser
    ````
   http://localhost:<APPLICATION_PORT>/graphiql?path=/graphql
  ````
   APPLICATION_PORT - Refer to application console logs ("Tomcat started on port" 8093 (http))
  
### Altair GraphQL Client

#### GraphQL Query
#### Query Student
###### Request
````query {
  getStudentById(id : 1) {
    firstName
    lastName
    email
  }}
````


###### Response
```
{
  "data": {
    "getStudentById": {
      "id": 2,
      "firstName": "Naveen Kumar",
      "lastName": "D C",
      "email": "test@example.com"
    }
  }
}
```
#### Query Student with Address
###### Request
````
query {
  getStudentById(id : 2) {
    id
    firstName
    lastName
    email
    address {
      id
      city
      street
    }
  }
}
````


###### Response
```
{
  "data": {
    "getStudentById": {
      "id": 2,
      "firstName": "Naveen Kumar",
      "lastName": "D C",
      "email": "test@example.com",
      "address": {
        "id": 2,
        "city": "Bangalore",
        "street": "HSR"
      }
    }
  }
}
```


#### Query Student With Address and List Of Subjects
###### Request
````

query {
  getStudentById(id : 2) {
    id
    firstName
    lastName
    email
    address {
      id
      city
      street
    }
    learningSubjects{
      id
      subjectName
      marksObtained
    }
  }
}
````


###### Response
```
{
  "data": {
    "getStudentById": {
      "id": 2,
      "firstName": "Naveen Kumar",
      "lastName": "D C",
      "email": "test@example.com",
      "address": {
        "id": 2,
        "city": "Bangalore",
        "street": "HSR"
      },
      "learningSubjects": [
        {
          "id": 3,
          "subjectName": "Data Science",
          "marksObtained": 100
        },
        {
          "id": 4,
          "subjectName": "Computer Science",
          "marksObtained": 100
        }
      ]
    }
  }
}
```


#### GraphQL Mutation

#### Add Student
###### Request
````query {
  mutation createStudent {
  addStudent(
    request: {
      firstName: "Naveen Kumar"
      lastName: "D C"
      email: "test@example.com"
    }
  ) {
    id
    fullName
    firstName
    lastName
    email
  }
}
````


###### Response
```
{
  "data": {
    "addStudent": {
      "id": 6,
      "fullName": null,
      "firstName": "Naveen Kumar",
      "lastName": "D C",
      "email": "test@example.com"
    }
  }
}
```
#### Add Student with Address
###### Request
````
mutation createStudent {
  addStudent(
    request: {
      firstName: "Naveen Kumar"
      lastName: "D C"
      email: "test@example.com"
      address: { city: "Bangalore", street: "HSR" }
    }
  ) {
    id
    fullName
    firstName
    lastName
    email
    address {
      city
      street
    }
  }
}

````


###### Response
```
{
  "data": {
    "addStudent": {
      "id": 5,
      "fullName": null,
      "firstName": "Naveen Kumar",
      "lastName": "D C",
      "email": "test@example.com",
      "address": {
        "city": "Bangalore",
        "street": "HSR"
      }
    }
  }
}
```


#### Add Student With Address and List Of Subjects
###### Request
````
mutation createStudent {
  addStudent(
    request: {
      firstName: "Naveen Kumar"
      lastName: "D C"
      email: "test@example.com"
      address: { city: "Bangalore", street: "HSR" }
      subject: [
        { subjectName: "Computer Science", marksObtained: 100 }
        { subjectName: "Data Science", marksObtained: 100 }
      ]
    }
  ) {
    id
    fullName
    firstName
    lastName
    email
    address {
      city
      street
    }
    learningSubjects {
      subjectName
      marksObtained
    }
  }
}
````


###### Response
```
{
  "data": {
    "addStudent": {
      "id": 4,
      "fullName": null,
      "firstName": "Naveen Kumar",
      "lastName": "D C",
      "email": "test@example.com",
      "address": {
        "city": "Bangalore",
        "street": "HSR"
      },
      "learningSubjects": [
        {
          "subjectName": "Data Science",
          "marksObtained": 100
        },
        {
          "subjectName": "Computer Science",
          "marksObtained": 100
        }
      ]
    }
  }
}
```