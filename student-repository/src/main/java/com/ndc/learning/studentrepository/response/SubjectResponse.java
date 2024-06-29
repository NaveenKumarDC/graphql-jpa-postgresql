package com.ndc.learning.studentrepository.response;

import com.ndc.learning.studentrepository.entity.Subject;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */

@Getter
@Setter
public class SubjectResponse {

  private Long id;

  private String subjectName;

  private Double marksObtained;

  public SubjectResponse(Subject subject) {
    this.id = subject.getId();
    this.subjectName = subject.getSubjectName();
    this.marksObtained = subject.getMarksObtained();
  }
}
