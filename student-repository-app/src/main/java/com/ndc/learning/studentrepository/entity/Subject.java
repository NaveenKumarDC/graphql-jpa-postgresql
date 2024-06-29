package com.ndc.learning.studentrepository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "marks_obtained")
	private Double marksObtained;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
