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


# Enable Spring Security
Reference doc : https://spring.academy/guides/spring-spring-for-graphql-security

## POM.xml Dependency
```
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## Enable spring security
  To configure Spring Security going forward we encourage users to move to a component-based configuration. 
In the config package create a new class called SecurityConfig and annotate it with @Configuration and @EnableWebSecurity

### Add below Spring security annotations in your Security Config File
````java
@Configuration
@EnableWebSecurity
public class SecurityConfig {}
````

### Configure Filter chain in Security Config file created
Disable Cross-Site Request Forgery (CSRF)
The user should be authenticated for any request in the application.
Spring Security will never create an HttpSession and it will never use it to obtain the Security Context
Spring Security’s HTTP Basic Authentication support is enabled by default. However, as soon as any servlet based configuration is provided, HTTP Basic must be explicitly provided.
````java
@Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {
  return http
          .csrf(csrf -> csrf.disable()) // (1)
          .authorizeRequests(auth -> {
            auth.anyRequest().authenticated(); // (2)
          })
          .sessionManagement(
                  session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          ) // (3)
          .httpBasic(withDefaults()) // (4)
          .build();
}
````
### Details of security filter chain

1. Disable Cross-Site Request Forgery (CSRF)
2. The user should be authenticated for any request in the application.
3. Spring Security will never create an HttpSession and it will never use it to obtain the Security Context
4. Spring Security’s HTTP Basic Authentication support is enabled by default. However, as soon as any servlet based configuration is provided, HTTP Basic must be explicitly provided.

## Managed Users
The quickest way to configure users is by creating in-memory users. While this is great for demo purposes this probably isn’t something you will do in production. When you’re creating users you need a way to encode the user's password. In the following example, you will use the default password encoder which is NOT something you should use in production.

````java
@Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {

    // InMemory Users.
    //User with the ADMIN and USER Roles
    UserDetails admin = User.withUsername("Naveen")
            .password(encoder.encode("Naveen"))
            .roles("ADMIN", "USER")
            .build();

    //User with the ADMIN and USER Roles
    UserDetails user = User.withUsername("User")
            .password(encoder.encode("User"))
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(admin, user);
  }
````
### Associated roles to Graphql Query

```java
 @QueryMapping
  @Secured("ROLE_USER") // Specific ROLE
  public Student getStudentById(@Argument long id) {
    return studentService.getStudentById(id);
  }

  @MutationMapping
  @PreAuthorize("hasRole('ADMIN')") //If User has ADMIN role then this mutation is allowed 
  public Student addStudent(@Argument(name = "request") StudentRequest studentRequest) {
    return studentService.addStudent(studentRequest);
  }
```

### Start the Spring boot application - StudentRepositoryApplication

#### Access the Graphiql
URL : 

#### Request
````
mutation createStudent {
  addStudent(
    request: {
      firstName: "Naveen"
      lastName: "Kumar"
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

#### Response
````
{
  "errors": [
    {
      "message": "Forbidden",
      "locations": [
        {
          "line": 2,
          "column": 3
        }
      ],
      "path": [
        "addStudent"
      ],
      "extensions": {
        "classification": "FORBIDDEN"
      }
    }
  ],
  "data": null
}
````

#### To Authenticate
URL: http://localhost:8093/graphiql?path=/graphql

Refer Managed Users section for more details
Provide "Username": "Naveen" and "Password" : "Naveen"

After providing the Login details one can perform mutation to add student as this is allowed to Users with ADMIN role only