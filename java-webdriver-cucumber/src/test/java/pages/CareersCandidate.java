package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class CareersCandidate extends Page{

    @FindBy(xpath = "//input[@placeholder='Enter First Name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Enter Last Name']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Enter Email']")
    private WebElement email;

    @FindBy(xpath = "//input[@placeholder='Enter Password']")
    private WebElement password;

    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@placeholder='123 Main st']")
    private WebElement address;

    @FindBy(xpath = "//input[@placeholder='City']")
    private WebElement city;

    @FindBy(xpath = "//select[@class='form-control']/../select")
    private WebElement state;

    @FindBy(xpath = "//input[@placeholder='Zip code. Zip plus']")
    private WebElement zip;

    @FindBy(xpath = "//textarea[@placeholder='Enter detailed Summary']")
    private WebElement summary;

    @FindBy(xpath = "//button[@id='candidateSubmit']")
    private WebElement submit;



    public CareersCandidate create1(Map<String, String> candidate) {
        firstName.sendKeys(candidate.get("firstName"));
        lastName.sendKeys(candidate.get("lastName"));
        password.sendKeys(candidate.get("password"));
        confirmPassword.sendKeys(candidate.get("confirm password"));
        summary.sendKeys(candidate.get("summary"));
        email.sendKeys(candidate.get("email"));
        address.sendKeys(candidate.get("address"));
        city.sendKeys(candidate.get("city"));

        new Select(state).selectByValue(candidate.get("state"));
        zip.sendKeys(candidate.get("zip"));

        submit.click();
        return new CareersCandidate();
    }



}
