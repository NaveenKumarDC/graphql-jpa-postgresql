package com.ndc.learning.studentrepository.repository;

import com.ndc.learning.studentrepository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Naveen Kumar D C
 * Date: 25/06/29
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
