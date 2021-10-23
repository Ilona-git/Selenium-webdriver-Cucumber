package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getDriver;

public class QuoteResult extends Page {

    @FindBy(xpath = "//div[@id='quotePageResult']")
    private WebElement result;

    @FindBy(xpath = "//b[@name='agreedToPrivacyPolicy']")
    private WebElement agreed;

    @FindBy(xpath = "//b[@name='password']")
    private WebElement password;

    public String getResult() {
        return result.getText();
    }

    public String getAgreedPrivacy() {
        return agreed.getText();
    }
    public String getPassword() {
        return password.getText();
    }

}