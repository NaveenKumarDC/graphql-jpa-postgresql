package com.ndc.learning.studentrepository;


import com.ndc.learning.studentrepository.repository.StudentRepository;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Author: Naveen Kumar D C
 * Date: 29/06/24
 */
@Testable
public class ApplicationConfigTest {

  @Test
  public void bootstrapAppFromJavaConfig() {

    ApplicationContext context = new AnnotationConfigApplicationContext(StudentRepositoryApplication.class);
    assertThat(context, is(notNullValue()));
    assertThat(context.getBean(StudentRepository.class), is(notNullValue()));
  }

}
