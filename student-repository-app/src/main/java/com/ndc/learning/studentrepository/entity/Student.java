package com.ndc.learning.studentrepository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;
/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="student_id")
	@BatchSize(size = 50)
	private List<Subject> learningSubjects;
}
