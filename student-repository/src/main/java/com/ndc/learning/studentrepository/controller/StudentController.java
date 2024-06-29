package com.ndc.learning.studentrepository.controller;

import com.ndc.learning.studentrepository.entity.Address;
import com.ndc.learning.studentrepository.entity.Student;
import com.ndc.learning.studentrepository.repository.StudentRepository;
import com.ndc.learning.studentrepository.request.AddressDTO;
import com.ndc.learning.studentrepository.request.SampleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Author: Naveen Kumar D C
 * Email: naveendc012@gmail.com
 * LinkedIn: https://www.linkedin.com/in/naveen-kumar-dc/
 * Date: 26/06/24
 */

@Controller
public class StudentController {

  @Autowired
  StudentRepository studentRepository;

  @QueryMapping
  public Student getStudentById(@Argument long id) {
    return studentRepository.findById(id).get();
  }

  @MutationMapping
  public Student addStudent(@Argument(name = "request") SampleRequest sampleRequest) {
    Student student = new Student();
    student.setFirstName(sampleRequest.getFirstName());
    student.setLastName(sampleRequest.getLastName());
    student.setEmail(sampleRequest.getEmail());
    return studentRepository.save(student);
  }
}
