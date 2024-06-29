package com.ndc.learning.studentrepository.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */

@Getter
@Setter
public class StudentRequest {

  private String firstName;

  private String lastName;

  private String email;

  private AddressDTO address;

  private List<SubjectDTO> subject;

}
