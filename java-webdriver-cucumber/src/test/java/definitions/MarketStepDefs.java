package definitions;

import cucumber.api.java.bs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.Th;
import org.assertj.core.api.Assertions;
import org.assertj.core.error.AbstractShouldHaveTextContent;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.server.handler.SendKeys;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class MarketStepDefs {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        switch (page) {
            case "usps":
                getDriver().get("https://www.usps.com");
                break;
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            case "google":
                getDriver().get("https://www.google.com");
                break;
            case "yahoo":
                getDriver().get("https://www.yahoo.com/");
                break;
            case "converter":
                getDriver().get("https://www.unitconverters.net/");
                break;
            case "calculator":
                getDriver().get("https://www.calculator.net/");
                break;
            case "ecosia":
                getDriver().get("https://www.ecosia.org/");
                break;
            case "ups":
                getDriver().get("https://www.ups.com/us/en/Home.page");
                break;
            case "careers":
                   getDriver().get("http://skryabin-careers.herokuapp.com/");
            break;
            default:
//                    System.out.println("Not recognized page " + page);
                throw new RuntimeException("Not recognized page " + page);
        }
    }

    @And("print page details in console")
    public void printPageDetailsInConsole() {
        System.out.println();
        System.out.println(getDriver().getCurrentUrl());
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getWindowHandles());
        System.out.println(getDriver().getPageSource());


    }

    @When("I verify email field behavior")
    public void iVerifyEmailFieldBehavior() {
        System.out.println();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ilonagorodzetskayagmail.com");
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ilonagorodzetskaya@gmail.com");
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();

    }

    @And("I go back and forward, then refresh the page")

    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().to("https://www.google.com");
        getDriver().navigate().forward();
        getDriver().navigate().back();
        getDriver().navigate().refresh();

    }


//Scenario: Market optional fields

    @And("I fill out required fields")
    public void iFillOutRequiredFields() {
//  WebDriver browser = getDriver();

//  String myXpath = "//input[@id='name']";
//  WebElement element =browser.findElement(By.xpath(myXpath));

//  String myXpath = "//input[@id='name']";
//  WebElement element =browser.findElement(By.xpath(mySelector));

        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("ilona");
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("ilonagor");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ilonagorodzetskaya@gmail.com");
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();


        //@And("And I fill out optional fields")
        //  public void andIFillOutOptionalFields() {
        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']/option[@value='Belarus']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).sendKeys("irvine");
        getDriver().findElement(By.xpath("//span[contains(text(),'Female')]")).click();
        getDriver().findElement(By.xpath("//option[contains(text(),'BMW')]")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        // }
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
    }


    @Then("I verify required fields")
    public void iVerifyRequiredFields() {

        String result = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
        String privacy = getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText();
        String password = getDriver().findElement(By.xpath("//b[@name='password']")).getText();

        assertThat(result).contains("ilona");
        assertThat(result).contains("ilonagor");
        assertThat(result).contains("ilonagorodzetskaya@gmail.com");

        assertThat(privacy).isEqualTo("true");
        assertThat(password).isEqualTo("[entered]");

//        assertThat(5).isBetween(3, 10);
//        assertThat(true).isTrue();
//        assertThat("string").isEqualTo("string");
//        assertThat("string").contains("str");
    }


    @Then("I verify optional fields")
    public void iVerifyOptionalFields() {

        String actualPhoneValue = getDriver().findElement(By.xpath("//b[@name='phone']")).getText();
        assertThat(actualPhoneValue).contains("123456789");
        String result = getDriver().findElement(By.xpath("//div[@class='well form-container container-fluid']")).getText();

        assertThat(result).contains("123456789");
        assertThat(result).contains("Belarus");
        assertThat(result).contains("BMW");
        assertThat(result).contains("female");

    }


    @And("I change resolution to {string} and then to {string}")
    public void iChangeResolutionToAndThenTo(String arg0, String arg1) {
        getDriver().manage().window().setSize(new Dimension(400, 768));
        getDriver().manage().window().setSize(new Dimension(1024, 768));
    }


    @Then("I verify that submitted fields saved correctly")
    public void iVerifyThatSubmittedFieldsSavedCorrectly() {

        String result = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
        String privacy = getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText();
        String password = getDriver().findElement(By.xpath("//b[@name='password']")).getText();
        System.out.println();

        //if (!result.contains("ilona")){
        //throw new RuntimeException("does not contain ilona");}

        // or you can use assertion instead of if

        assertThat(result).contains("ilona");
        assertThat(result).contains("ilonagor");
        assertThat(result).contains("ilonagorodzetskaya@gmail.com");
        assertThat(privacy).isEqualTo("true");
        assertThat(password).isEqualTo("[entered]");
    }


    //   @And("I fill out optional fields")
    //   public void iFillOutOptionalFields() throws InterruptedException {
    //       WebElement countrySelect = getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']"));
    //     new Select(countrySelect).selectByValue("Belarus");
    //     new Select(countrySelect).selectByValue("China");
    // or:
//        Select mySelect = new Select(countrySelect);
//        new Select(countrySelect).selectByValue("Belarus");
    //       Thread.sleep(5000);
//    }

    @And("I print logs to the console")
    public void iPrintLogsToTheConsole() throws InterruptedException {
        Thread.sleep(2000);
        LogEntries logs = getDriver().manage().logs().get("browser");  //we get the logs and save them in logs file
        System.out.println("logs. Start");
        for (LogEntry log : logs) {
            System.out.println(log);
        }
        System.out.println("logs. End");
    }

    @And("I fill multu-select")
    public void iFillMultuSelect() throws InterruptedException {
        WebElement ford = getDriver().findElement(By.xpath("//select[@name='carMake']/option[@value='Ford']"));
        WebElement bmw = getDriver().findElement(By.xpath("//select[@name='carMake']/option[@value='BMW']"));

        Actions actions = new Actions(getDriver());
        actions
                .click(ford) //every click already mouseover
                .keyDown(Keys.CONTROL)
                .moveToElement(bmw)
                .click()
                .perform();
//or

        //       WebElement carsElement = getDriver().findElement(By.xpath("//select[@name='carMake']"));
        //       Select carsSelect = new Select(carsElement);
        //       carsSelect.selectByValue("BMW");
        //       carsSelect.selectByValue("Ford");

        Thread.sleep(3000);
    }

    @And("I {string} third party agreement")
    public void iThirdPartyAgreement(String action) {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        if (action.equals("accept")) {
            getDriver().switchTo().alert().accept();
        } else {
            getDriver().switchTo().alert().dismiss();
        }
    }

    @And("fill out additional info with name {string} and phone {string}")
    public void fillOutAdditionalInfoWithNameAndPhone(String arg0, String arg1) {
        getDriver().switchTo().frame("additionalInfo"); //go to inside iframe first

        getDriver().findElement(By.xpath("//input[@id='contactPersonName']")).sendKeys("John Doe");
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys("1234567890");

        //next you hate to swich out from iframe
        getDriver().switchTo().defaultContent();
        //if you have a multiple iframe
        getDriver().switchTo().frame("another Iframe");
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys("1234567890");
        getDriver().switchTo().defaultContent();
        //if you want switch to other frame inside another iframe(up)
        //getDriver().switchTo().parentFrame();
    }

    @And("I verify {string} present on related docs page")
    public void iVerifyPresentOnRelatedDocsPage(String docName) {
        //       getDriver().getWindowHandle(); //string
        //       getDriver().getWindowHandles(); //set, all elements in set are unique

        String originalWindow = getDriver().getWindowHandle(); //save originalWindow
        getDriver().findElement(By.xpath("//button[contains(@onclick, 'new')]")).click();//click on button which opens up new window

        //switch to last one
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }
        String docs = getDriver().findElement(By.xpath("//body")).getText();
        assertThat(docs).contains(docName);
        getDriver().close(); //closes active window
//switch back to original window
        getDriver().switchTo().window(originalWindow);
    }

    @And("I fill out search engine field with {string} and search")
    public void iFillOutSearchEngineFieldWithAndSearch(String searchValue) {
        getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys(searchValue);
        WebElement button = getDriver().findElement(By.xpath("//button[@type='submit']"));
        //   JavascriptExecutor js = (JavascriptExecutor) getDriver();
        getExecutor().executeScript("arguments[0].click();", button);
        System.out.println();


    }


    @And("I test waits")
    public void iTestWaits() {

        SimpleDateFormat format = new SimpleDateFormat("h:mm:ss a zzzz");
        System.out.println(format.format(new Date()));
        try {
//            getDriver().findElement(By.xpath("//notpresent"));
            getWait(10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//notpresent")));
//            getWait(10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//notpresent")));
//            getWait(10).until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//notpresent"))));
        } catch (Exception e) {
            System.out.println(format.format(new Date()));
        }
    }


}



