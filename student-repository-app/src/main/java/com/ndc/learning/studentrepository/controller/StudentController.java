package com.ndc.learning.studentrepository.controller;

import com.ndc.learning.studentrepository.entity.Student;
import com.ndc.learning.studentrepository.repository.StudentRepository;
import com.ndc.learning.studentrepository.request.StudentRequest;
import com.ndc.learning.studentrepository.response.StudentResponse;
import com.ndc.learning.studentrepository.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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
  public Student getStudentById(@Argument long id) {
    return studentService.getStudentById(id);
  }

  @MutationMapping
  public Student addStudent(@Argument(name = "request") StudentRequest studentRequest) {
    return studentService.addStudent(studentRequest);
  }
}
