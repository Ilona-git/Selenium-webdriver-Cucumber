package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import definitions.CareersStepDefs;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static support.TestContext.*;

public class Hooks {

    @Before(order = 0)
    public void scenarioStart() {
        TestContext.setTimestamp();
        TestContext.initialize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(getConfig().implicitTimeout, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(getConfig().pageLoadTimeout, TimeUnit.SECONDS);
    }


    @Before(value = "@create_position")
    public void createPosition() {
        new RestWrapper()
                .login(getData("recruiter"))
                .createPosition(getPosition("automation"));
    }

    @After(value = "@delete_position")
    public void deletePosition() {
        new RestWrapper()
                .login(getData("recruiter"))
                .deletePositionById(getTestDataMap("newPosition").get("id"));
    }

    @After(value = "@delete_position_if_failed")
    public void deletePositionIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            deletePosition();
        }
    }

    @After(order = 0)
    public void scenarioEnd(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
            byte[] screenshot = screenshotTaker.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        TestContext.teardown();
    }
}