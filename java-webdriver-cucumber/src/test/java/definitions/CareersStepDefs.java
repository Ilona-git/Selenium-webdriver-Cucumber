package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class CareersStepDefs {

    Map<String, String> position = getPosition("automation");

    @Given("I open {string} page")
    public void iOpenPage(String page) {
        switch (page) {
            case "quote":
                new QuoteForm().open();
                break;
            case "careers":
                new CareersLanding().open();
                break;
            default:
                throw new RuntimeException("Page is not supported: " + page);
        }
    }

    @And("I login as {string}")
    public void iLoginAs(String role) {
        Map<String, String> user = getData(role);
        new CareersLanding()
                .clickLogin()
                .login(user);
    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String role) {
        Map<String, String> user = getData(role);
        String actualName = new CareersLanding().getLoggedUserName();
        String expectedName = user.get("name");
        assertThat(actualName).isEqualTo(expectedName);
    }

    @When("I remove {string} position")
    public void iRemovePosition(String title) {
        new CareersLanding()
                .clickRecruit()
                .removePosition(title);
    }

    @And("I verify {string} position is removed")
    public void iVerifyPositionIsRemoved(String title) {
        new CareersRecruit().refresh();
        assertThat(new CareersRecruit().areErrorsPresent()).isFalse();
        assertThat(new CareersRecruit().isPositionVisible(title)).isFalse();
    }

    @When("I create new position")
    public void iCreateNewPosition() {
        new CareersLanding()
                .clickRecruit()
                .clickNewPosition()
                .create(position);
    }

    @Then("I verify new position is created")
    public void iVerifyNewPositionIsCreated() {
        boolean isCreated = new CareersRecruit().isPositionVisible(position.get("title"));
        assertThat(isCreated).isTrue();
    }

    @When("I remove new position")
    public void iRemoveNewPosition() {
        new CareersRecruit().removePosition(position.get("title"));
    }

    @And("I verify new position is removed")
    public void iVerifyNewPositionIsRemoved() {
        boolean isVisible = new CareersRecruit().isPositionVisible(position.get("title"));
        assertThat(isVisible).isFalse();
    }

    @Given("I use cascading calls")
    public void iUseCascadingCalls() {
        Map<String, String> user = getData("recruiter");
        boolean isVisible = new CareersLanding()
                .clickLogin()
                .login(user)
                .clickRecruit()
                .clickNewPosition()
                .create(position)
                .removePosition(position.get("title"))
                .isPositionVisible(position.get("title"));
        assertThat(isVisible).isFalse();
    }

    @When("I update new position")
    public void iUpdateNewPosition() {
        // UI interaction
        Map<String, Object> newPosition = getTestDataMap("newPosition");
        System.out.println();
    }
}