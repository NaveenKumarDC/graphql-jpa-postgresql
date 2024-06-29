package com.ndc.learning.studentrepository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */


@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @OneToOne(mappedBy = "address")
  private Student student;

}
