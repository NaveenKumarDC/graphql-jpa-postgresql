package com.ndc.learning.studentrepository.controller;

import com.ndc.learning.studentrepository.entity.Student;
import com.ndc.learning.studentrepository.request.StudentRequest;
import com.ndc.learning.studentrepository.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

/**
 * Author: Naveen Kumar D C
 * Email: naveendc012@gmail.com
 * LinkedIn: https://www.linkedin.com/in/naveen-kumar-dc/
 * Date: 26/06/24
 */

@Controller
public class StudentController {

  @Autowired
  private StudentService studentService;

  @QueryMapping
  @Secured("ROLE_USER")
  public Student getStudentById(@Argument long id) {
    return studentService.getStudentById(id);
  }

  @MutationMapping
  @PreAuthorize("hasRole('ADMIN')") //If User has ADMIN role then this mutation is allowed
  public Student addStudent(@Argument(name = "request") StudentRequest studentRequest) {
    return studentService.addStudent(studentRequest);
  }

  @QueryMapping
  public Student getStudentByFirstName(@Argument(name = "firstName") String firstName) {
    return studentService.getStudentByFirstName(firstName);
  }
}
