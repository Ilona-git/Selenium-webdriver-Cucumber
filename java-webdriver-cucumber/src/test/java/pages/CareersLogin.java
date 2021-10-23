package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

import static support.TestContext.getExecutor;

public class CareersLogin extends CareersHeader {

    // static locators
    @FindBy(xpath = "//label[@for='loginUsername']/../input")
    private WebElement username;

    @FindBy(xpath = "//label[@for='loginPassword']/../input")
    private WebElement password;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    // micro methods

    public CareersLanding login(Map<String, String> user) {
        fill(username, user.get("email"));
        fill(password, user.get("password"));
        click(loginButton);
        return new CareersLanding();
    }

    public CareersLanding login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();
        return new CareersLanding();
    }


}