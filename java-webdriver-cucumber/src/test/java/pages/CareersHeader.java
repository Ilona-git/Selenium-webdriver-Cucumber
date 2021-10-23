package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersHeader extends Page {

    @FindBy(xpath = "//a[@href='/login']/button")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='logout-box']/a")
    private WebElement loggedUserName;

    @FindBy(xpath = "//a[@href='/recruit']/button")
    private WebElement recruit;

    public CareersRecruit clickRecruit() {
        recruit.click();
        return new CareersRecruit();
    }

    public CareersLogin clickLogin() {
        loginButton.click();
        return new CareersLogin();
    }

    public String getLoggedUserName() {
        return loggedUserName.getText();
    }
}