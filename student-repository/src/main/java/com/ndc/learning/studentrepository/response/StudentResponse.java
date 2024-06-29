package com.ndc.learning.studentrepository.response;

import com.ndc.learning.studentrepository.entity.Student;
import com.ndc.learning.studentrepository.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */

@Setter
@Getter
public class StudentResponse {

  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private String street;

  private String city;

  private List<SubjectResponse> learningSubjects;

  // this is for internal use. DO NOT PUT IN SCHEMA
  private Student student;

  private String fullName;

  public StudentResponse(Student student) {
    this.student = student;
    this.id = student.getId();
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
    this.email = student.getEmail();

    this.street = student.getAddress().getStreet();
    this.city = student.getAddress().getCity();

    if (student.getLearningSubjects() != null) {
      learningSubjects = new ArrayList<SubjectResponse>();
      for (Subject subject : student.getLearningSubjects()) {
        learningSubjects.add(new SubjectResponse(subject));
      }
    }
  }

}
