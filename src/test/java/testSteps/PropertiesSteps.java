package testSteps;

import app.properties.AppProps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class PropertiesSteps {

    @Given("I have a property file test.properties")
    public void iHaveAPropertyFileEnvProperties() {
        // Empty step. Used only to make test more readable.
    }


    @Then("I can print the value of property: baseUrl")
    public void iCanPrintTheValueOfBaseUrl() {
        // Demonstrative test step to show how test properties are retrieved.
        System.out.println(AppProps.BASE_URL);
    }

    @Then("I can get value of a non mandatory property")
    public void iCanGetValueOfPropertyNot_mandatory_property() {
        // Demonstrative test step to show how test properties are retrieved.
        System.out.println(AppProps.EXTENDED_TIMEOUT);
    }
}
