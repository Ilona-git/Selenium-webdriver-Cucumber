package definitions;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static support.TestContext.getDriver;


public class learnSteps {
    @And("I open browser")
    public void iOpenBrowser() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        driver.getCurrentUrl();
        driver.getWindowHandle();



    }

}