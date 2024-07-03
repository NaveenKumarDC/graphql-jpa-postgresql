package com.ndc.learning.studentrepository;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Author: Naveen Kumar D C
 * Date: 29/06/24
 */
@Testable
public class StudentJunitTest {

  @Test
  public void testStudentAdding(){
    WebTestClient client =
            WebTestClient.bindToServer()
                    .baseUrl("http://localhost:8093/graphql")
                    .defaultHeader("Username", "Naveen")
                    .defaultHeader("Password", "Naveen")
                    .build();

    HttpGraphQlTester graphQlTester = HttpGraphQlTester.create(client);
    String document = " query {\n" +
            " getStudentById(id : 1) {\n" +
            "    firstName\n" +
            "    lastName\n" +
            "    email\n" +
            "  }\n" +
            "}";

    graphQlTester.document(document)
            .execute()
            .path("data.getStudentById.firstName")
            .entityList(String.class)
            .hasSize(1);
  }
}
