package com.epam.cucumber;

import com.epam.dto.request.UpdatePassword;
import com.epam.dto.response.CredentialsDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UserControllerCucumberTest {

    private UpdatePassword updatePassword;

    private CredentialsDto credentialsDto;
    private ResponseEntity<String> response;

    private RestTemplate restTemplate=new RestTemplate();

    @Given("the user has a valid update password request")
    public void the_user_has_a_valid_update_password_request() {
        updatePassword = UpdatePassword.builder().userName("stringstring604910999").oldPassword("$2a$10$EuWzIAgPCQUpohLqIbbS/.DoCE6f6oCBwXp0GcJYTosX3yzcdzZfm").newPassword("lavanya@2000").build();
    }
    @When("the user submits the update password request")
    public void the_user_submits_the_update_password_request() {
        String url = "http://localhost:8081/gym/user/updatePassword"; // Update with your actual API URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdatePassword> requestEntity = new HttpEntity<>(updatePassword, headers);
        response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }
    @Then("the system should update the user's password successfully")
    public void the_system_should_update_the_user_s_password_successfully() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Then("the system should respond with HTTP status code {int}")
    public void the_system_should_respond_with_http_status_code(Integer int1) {
        assertEquals(int1, response.getStatusCode().value());
    }





}
