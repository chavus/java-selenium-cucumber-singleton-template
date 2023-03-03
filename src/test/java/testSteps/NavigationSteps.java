package testSteps;


import app.pages.BasePage;
import app.pages.Home;
import app.pages.Installation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationSteps {
    @Given("I go to the Cucumber main page")
    public void iAmInTheCucumberMainPage() {
        Home.go();
    }

    @When("I select View Docs from content")
    public void iSelectViewDocsFromContent() {
        Home.clickViewDocs();
    }

    @Then("I am redirected to the documentation page")
    public void iAmRedirectedToTheDocumentationPage() {
        assertTrue(Installation.imHere());
    }

    @When("I select View Docs from navbar")
    public void iSelectViewDocsFromNavbar() {
        BasePage.selectOptionAndSubOptionFromMenu("Docs","Installation");
    }
}
