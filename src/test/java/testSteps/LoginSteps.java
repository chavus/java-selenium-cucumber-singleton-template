package testSteps;

import app.pages.BasePage;
import app.pages.Login;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    @When("I go to Login page")
    public void iGoToLoginPage() {
        BasePage.clickLogin();
    }

    @When("I enter email:{string} and password:{string}")
    public void iEnterEmailAndPassword(String username, String password) {
        Login.attemptToLogin(username, password);
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String errorMessage) {
        assertEquals(errorMessage, Login.getErrorMessage());
    }

}
