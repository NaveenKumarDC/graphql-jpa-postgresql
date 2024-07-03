package com.ndc.learning.studentrepository.service;

import com.ndc.learning.studentrepository.entity.Student;
import com.ndc.learning.studentrepository.request.StudentRequest;

/**
 * Author: Naveen Kumar D C
 * Date: 29/06/24
 */
public interface StudentService {

  /**
   * Add student into student table.
   * @param studentRequest request exposed to controllers.
   * @return Student entity.
   */
  Student addStudent(StudentRequest studentRequest);

  /**
   * Get student by Id
   * @param studentId
   * @return
   */
  Student getStudentById(Long studentId);



  /**
   * Get student by FirstName using the typed query
   * @param firstName
   * @return
   */
  Student getStudentByFirstName(String firstName);
}
