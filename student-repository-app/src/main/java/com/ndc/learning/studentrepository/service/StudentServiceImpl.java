package com.ndc.learning.studentrepository.service;

import com.ndc.learning.studentrepository.entity.Address;
import com.ndc.learning.studentrepository.entity.Student;
import com.ndc.learning.studentrepository.entity.Subject;
import com.ndc.learning.studentrepository.repository.AddressRepository;
import com.ndc.learning.studentrepository.repository.StudentRepository;
import com.ndc.learning.studentrepository.repository.SubjectRepository;
import com.ndc.learning.studentrepository.request.StudentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Naveen Kumar D C
 * Date: 29/06/24
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Override
  public Student addStudent(StudentRequest studentRequest) {
    Student student = new Student();
    student.setFirstName(studentRequest.getFirstName());
    student.setLastName(studentRequest.getLastName());
    student.setEmail(studentRequest.getEmail());
    Address address = new Address();
    if(Objects.nonNull(studentRequest.getAddress())) {
      address.setCity(studentRequest.getAddress().getCity());
      address.setStreet(studentRequest.getAddress().getStreet());
      student.setAddress(addressRepository.save(address));
    }
    Student createdStudent = studentRepository.save(student);
    createdStudent.setAddress(address);
    List<Subject> createdSubjects = new ArrayList<>();
    if(!CollectionUtils.isEmpty(studentRequest.getSubject())) {
      studentRequest.getSubject().parallelStream().forEach(subjectDTO -> {
        Subject subject = new Subject();
        subject.setStudent(createdStudent);
        subject.setSubjectName(subjectDTO.getSubjectName());
        subject.setMarksObtained(subjectDTO.getMarksObtained());
        createdSubjects.add(subjectRepository.save(subject));
      });
    }
    createdStudent.setLearningSubjects(createdSubjects);
    return createdStudent;
  }

  @Override
  public Student getStudentById(Long studentId) {
    return studentRepository.findById(studentId).get();
  }
}
