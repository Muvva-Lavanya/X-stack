package com.epam;

import cucumber.api.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@CucumberOptions(features="UserController.feature",glue="UserControllerTest.class")
public class CucumberTest {

       @Test
       void contextLoads() {
   }
}
