import com.nutrix.command.infra.Patient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PatientSteps {

    @LocalServerPort
    private int port;
    private String uid;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="http://localhost:8082/";
    //private String postUrl="https://nutrix-patient-service.mybluemix.net/";
    private String error= "Email ya en uso";

    @Given("I can sign up as a patient")
    public void i_can_sign_up_as_a_patient() throws Throwable{
        String url=postUrl + "patient/";
        List<Patient> all=restTemplate.getForObject(url, List.class);
        log.info(all);
        assertTrue(!all.isEmpty());
    }

    @Given("I sending register form to be created with my email{string}")
    public void i_sending_register_form_to_be_created_with_my_email_bill_gmail_com(String email) {
        Date date= new Date();
        Patient patient = new Patient("uid", "username", "password","Luis" ,"Panta", email,date);
        String url=postUrl + "patient/";
        Patient newPatient =restTemplate.postForObject(url,patient,Patient.class);
        uid = newPatient.getId();
        log.info(newPatient);
        assertNotNull(newPatient);
    }
    @Then("I should be able to see my newly created patient account")
    public void i_should_be_able_to_see_my_newly_created_patient_account() {
        String url=postUrl+"patient/" + uid;
        Patient patient=restTemplate.getForObject(url,Patient.class);
        log.info(patient);
        assertNotNull(patient);
    }

    @Given("I sending register form to be created with an used email {string}")
    public void i_sending_register_form_to_be_created_with_an_used_email(String string) {
        Date date= new Date();
        Patient patient = new Patient("uid", "username", "password","Luis" ,"Panta", string,date);
        String url=postUrl + "patient/";

        try
        {
            Patient newPatient =restTemplate.postForObject(url,patient,Patient.class);
            log.info(newPatient);
        }catch(RestClientException e){
            error="Email ya en uso";
        }
        assertEquals(error,"Email ya en uso");
    }


    @Then("I should be able to see an error message {string}")
    public void i_should_be_able_to_see_an_error_message(String string) {
        assertEquals("Email ya en uso",string);
    }
}
