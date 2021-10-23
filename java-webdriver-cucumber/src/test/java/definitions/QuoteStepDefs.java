package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import pages.QuoteForm;
import pages.QuoteResult;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
import static support.TestContext.*;

public class QuoteStepDefs {

    QuoteForm formPage = new QuoteForm();
    QuoteResult resultPage = new QuoteResult();

    Map<String, String> user = getData("user");

    @Given("I go to {string} page oop")
    public void iGoToPageOop(String page) {

        formPage.open();
    }

    @When("I fill out required fields as {string} oop")
    public void iFillOutRequiredFieldsOop(String role) {
        Map<String, String> user = getData(role);
        formPage.fillUsername(user.get("username"));
        formPage.fillEmail(user.get("email"));
        formPage.fillBothPasswordsFields(user.get("password"));
        formPage.fillName(user.get("firstName"), user.get("lastName"));
        formPage.clickAgreedPrivacy();
    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        formPage.clickSubmit();
    }

    @Then("I verify required fields for {string} oop")
    public void iVerifyRequiredFieldsOop(String role) {
        Map<String, String> user = getData(role);
        assertThat(resultPage.getResult()).contains(user.get("username"));
        assertThat(resultPage.getResult()).contains(user.get("email"));
        assertThat(resultPage.getResult()).doesNotContain(user.get("password"));
        assertThat(resultPage.getResult()).contains(user.get("firstName"));
        assertThat(resultPage.getResult()).contains(user.get("lastName"));

        assertThat(resultPage.getAgreedPrivacy()).isEqualTo("true");
        assertThat(resultPage.getPassword()).isEqualTo("[entered]");

    }

    //quote3
    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String field, String value) {
        switch (field) {
            case "username":
                formPage.fillUsername(value);
                break;
            case "email":
                formPage.fillEmail(value);
                break;
            case "password":
                formPage.fillPassword(value);
                break;
            case "confirmPassword":
                formPage.fillConfirmPassword(value);
                break;
            default:
                throw new RuntimeException("Unknown field: " + field);
        }
    }

    @Then("I see {string} error message {string}")
    public void iSeeErrorMessage(String field, String expectedMessage) throws InterruptedException {
        Thread.sleep(5000);
        String actualMessage = formPage.getErrorText(field);
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Then("I don't see {string} error message")
    public void iDonTSeeErrorMessage(String field) {
        assertThat(formPage.isErrorDisplayed(field)).isFalse();
    }


    @And("I see a {string} error message {string}")
    public void iSeeAErrorMessage(String arg0, String arg1) {


    }


    //quote2
    @When("I clear {string} field")
    public void iClearField(String arg0) {
//     QuoteForm form = new QuoteForm();
        formPage.clearUsername();
    }

    @And("I clear also {string} field")
    public void iClearAlsoField(String arg0) {
        formPage.clearEmail();
    }

    @And("I clear c {string} field")
    public void iClearCField(String arg0) {
        formPage.clearConfirmPassword();
    }

    @And("I clear p {string} field")
    public void iClearPField(String arg0) {
        formPage.clearPassword();
    }
    @And("I clear n {string} field")
    public void iClearNField(String arg0) {
        formPage.clearName();
    }

    @And("I uncheck {string} field")
    public void iUncheckField(String arg0) {
        formPage.JavaClickAgreedPrivacy();
    }

    @When("I fill out name field with first name {string} and last name {string}")
    public void iFillOutNameFieldWithFirstNameAndLastName(String firstName, String lastName) {
        formPage.fillName(user.get("firstName"), user.get("lastName"));
    }

    @Then("I verify {string} field value {string}")
    public void iVerifyFieldValue(String name, String a) {
       String namefield = getDriver().findElement(By.xpath("//input[@value='" + a + "']")).getAttribute("value");
       assertThat(namefield).contains(a);
    }


    @When("I fill out name field with first name {string}, middle name {string}, last name {string}")
    public void iFillOutNameFieldWithFirstNameMiddleNameLastName(String first, String middle, String last) {
        formPage.clearName();
        formPage.fillFirstMiddleLastName(first, middle, last);

    }
}



