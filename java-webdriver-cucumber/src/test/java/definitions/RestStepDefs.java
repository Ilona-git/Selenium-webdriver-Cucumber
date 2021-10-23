package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import support.Loggable;
import support.RestWrapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class RestStepDefs implements Loggable {

    @Given("I login via REST as {string}")
    public void iLoginViaRESTAs(String role) {
        setTimestamp();
        Map<String, String> user = getData(role);
        new RestWrapper().login(user);

        getLogger().debug("my message");
    }

    @When("I create via REST {string} position")
    public void iCreateViaRESTPosition(String type) {
        Map<String, String> position = getPosition(type);
        new RestWrapper().createPosition(position);
    }


    //rest2
    @When("I create via REST {string} candidate")
    public void iCreateViaRESTCandidate(String type) {
        Map<String, String> candidate = getCandidate(type);
        new RestWrapper().createCandidate(candidate);

    }


    @Then("I verify via REST new position is in the list")
    public void iVerifyViaRESTNewPositionIsInTheList() {
        List<Map<String, Object>> actualPositions = new RestWrapper().getPositions();
        Map<String, Object> expectedPosition = getTestDataMap("newPosition");

        getLogger().info("Verifying if position in the list");
        boolean isFound = false;
        for (Map<String, Object> actualPosition : actualPositions) {
            if (actualPosition.get("id").equals(expectedPosition.get("id"))) {
                isFound = true;
                for (String key : expectedPosition.keySet()) {
                    Object expected = expectedPosition.get(key);
                    Object actual = actualPosition.get(key);
                    System.out.println("Verifying: " + key);
                    System.out.println("Expected: " + expected);
                    System.out.println("Actual: " + actual);
                    assertThat(actual).isEqualTo(expected);
                }
                break;
            }
        }
        assertThat(isFound).isTrue();
    }

    @When("I update via REST {string} position")
    public void iUpdateViaRESTPosition(String type) {
        Map<String, String> updatedPosition = getPosition(type + "_updated");
        Object id = getTestDataMap("newPosition").get("id");
        new RestWrapper().updatePosition(updatedPosition, id);
    }

    @Then("I verify via REST new position is updated")
    public void iVerifyViaRESTNewPositionIsUpdated() {
        Map<String, Object> expectedPosition = getTestDataMap("newPosition");
        Object id = expectedPosition.get("id");
        Map<String, Object> actualPosition = new RestWrapper().getPositionById(id);
        for (String key : expectedPosition.keySet()) {
            assertThat(actualPosition.get(key)).isEqualTo(expectedPosition.get(key));
        }
    }

    @When("I delete via REST new position")
    public void iDeleteViaRESTNewPosition() {
        Object id = getTestDataMap("newPosition").get("id");
        new RestWrapper().deletePositionById(id);
        LogManager.getLogger(RestStepDefs.class).error("Error in" +
                " deletion");
    }

    @Then("I verify via REST new position is deleted")
    public void iVerifyViaRESTNewPositionIsDeleted() {
        List<Map<String, Object>> actualPositions = new RestWrapper().getPositions();
        Object deletedId = getTestDataMap("newPosition").get("id");
        for (Map<String, Object> position : actualPositions) {
            assertThat(position.get("id")).isNotEqualTo(deletedId);
        }
    }


    //  @rest2
    @Then("I verify via REST new candidate is in the list")
    public void iVerifyViaRESTNewCandidateIsInTheList() {
        List<Map<String, Object>> actualCandidates = new RestWrapper().getCandidates();
        Map<String, Object> expectedCantidate = getTestDataMap("newCandidate");

        getLogger().info("Verifying if position in the list");
        boolean isFound = false;
        for (Map<String, Object> actualCandidate : actualCandidates) {
            if (actualCandidate.get("id").equals(expectedCantidate.get("id"))) {
                isFound = true;
                for (String key : expectedCantidate.keySet()) {
                    Object expected = expectedCantidate.get(key);
                    Object actual = actualCandidate.get(key);
                    System.out.println("verifying: " + key);
                    System.out.println("expected: " + expected);
                    System.out.println("actual: " + actual);
                    assertThat(actual).isEqualTo(expected);
                }
                break;
            }
        }
        assertThat(isFound).isTrue();

    }
}