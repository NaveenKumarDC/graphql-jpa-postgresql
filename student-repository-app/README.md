Spring boot application with Graphql

Components Used
    - Spring
      - Spring boot 3+
      - Spring Graphql
      - Spring JPA + Posgtresql 15
      - Java
        - Java 17+

Resources
    - GraphQL Spec : https://spec.graphql.org/draft/#sec-Overview
    - Spring Graphql Guides : https://spring.io/guides/gs/graphql-server
    - Spring JPA : 

Requirement ?
- Install local postgresql 15
    - Create a database
        - Set USER_NAME and PASSWORD
        - Grant DDL creation permission
- Installing Git
- Download altair client - Optional for testing Graphql Endpoints


Why ?
 - To learn how to create Spring boot application with Graphql integration.


What ?
 - Creating a Student and accessing using the graphql end points
   - Query Student
   - Add Student

    
How ?
- Clone the Repository
- Setup local Posgress 
- Update application.properties file with below properties
  - ## PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/<DATA_BASE_NAME>
    spring.datasource.username=<USER_NAME>
    spring.datasource.password=<PASSWORD>
- Run spring boot application
  - Verify if Graphiql client started by checking the console logs
    - 2024-06-29T05:23:10.231+05:30  INFO 11879 --- [student-repository] [           main] s.b.a.g.s.GraphQlWebMvcAutoConfiguration : GraphQL endpoint HTTP POST /graphql
      2024-06-29T05:23:10.404+05:30  INFO 11879 --- [student-repository] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8093 (http) with context path '/'
- Go to web broweser
  - http://localhost:<APPLICATION_PORT>/graphiql?path=/graphql
    - APPLICATION_PORT - Refer to application console logs ("Tomcat started on port" 8093 (http))
- Go to Altair GraphQL Client
  - Query - To Get any student
    
    - Query
        "query {
              getStudentById(id : 1) {
                firstName
                lastName
                email
                fullName
              }
        }"
    
      - Response
    
        {
            "data": {
                "getStudentById": {
                    "firstName": "Naveen Kumar",
                    "lastName": "D C",
                    "email": "test@email.com",
                    "fullName": null
                }
            }
        }
  - Mutation - to add student copy below query to altair graphql client and hit
  
    - "mutation createStudent {
        addStudent(request: {firstName: "Naveen Kumar", lastName: "D C", email: "test@email.com"} ) {
          id
          fullName
          firstName
          lastName
          email
          }
      }"
    
      - Response
        {
            "data": {
                "addStudent": {
                    "id": 1,
                    "fullName": null,
                    "firstName": "Naveen Kumar",
                    "lastName": "D C",
                    "email": "test@email.com"
                }
            }
        }