package definitions;

import cucumber.api.java.bs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.Th;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

public class UspsStepdefs {
    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {
        WebElement mailandShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(getDriver()).moveToElement(mailandShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-zip']//a")).click();
        getDriver().findElement(By.xpath("//a[contains(@class,'zip-code-address')]")).click();
    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        WebElement stateElement = getDriver().findElement(By.xpath("//select[@id='tState']"));
        new Select(stateElement).selectByValue(state);
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();
    }


//  wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@id='zipByAddressDiv']"), "")));
//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='zipByAddressDiv']//p")));
//  wait.until(driver -> driver.findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText().length() > 0); //-> SHORT WAY (to make sure the text is not empty)

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String zip) throws InterruptedException {
        WebElement resultElement = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        //       wait.until(driver -> !resultElement.getText().isEmpty());
        //       String result = resultElement.getText();
        getWait().until(ExpectedConditions.textToBePresentInElement(resultElement, zip));

        List<WebElement> list = getDriver().findElements(By.xpath("//div[@class='zip_code_address_results active']"));
        for (WebElement item : list) {
            String itemText = item.getText();
            System.out.println(itemText);
            assertThat(itemText).contains(zip);
        }
    }



// @usps4
//  Scenario: Phone number of the nearest Accountable Mail Pickup Service Post Office for Portnov Computer School
    @When("I go to Find a Location Page")
    public void iGoToFindALocationPage() {

        WebElement mailShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        getActions().moveToElement(mailShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-find']//a")).click();

    }

    @And("I filter by {string} Location Types, {string} Services, {string} Available Services")
    public void iFilterByLocationTypesServicesAvailableServices(String locationType, String service, String availableService) throws InterruptedException {
        getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='" + locationType + "']")).click();

        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='" + service + "']")).click();

        getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='" + availableService + "']")).click();
        Thread.sleep(3000);
    }

    @And("I fill in {string} street, {string} city, {string} state")
    public void iFillInStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.xpath("//input[@id='search-input']")).click();

        WebElement address = getDriver().findElement(By.xpath("//input[@id='addressLineOne']"));
        //      getWait().until(ExpectedConditions.elementToBeClickable(address));
        address.sendKeys(street);

        for (int i = 0; !address.getAttribute("value").equals(street) && i < 5; i++) {
            address.clear();
            address.sendKeys(street);
        }

        //       getDriver().findElement(By.xpath("//input[@id='addressLineOne']")).sendKeys(street);

        getDriver().findElement(By.xpath("//input[@id='cityOrZipCode']")).sendKeys(city);

        WebElement stateEl = getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']"));
        new Select(stateEl).selectByValue(state);

        getDriver().findElement(By.xpath("//a[contains(text(),'Go to Results')]")).click();
    }

    @Then("I print the phone number and validate it is {string}")
    public void iPrintThePhoneNumberAndValidateItIs(String phone) throws InterruptedException {
        getDriver().findElement(By.xpath("(//div[@id='resultBox']/div)[1]")).click();
        String ActualPhone = getDriver().findElement(By.xpath("//p[@id='detailTollFree']")).getText();
        assertThat(ActualPhone).contains(phone);
    }





    // Scenario: Calculate price
    @When("I go to calculate price")
    public void iGoToCalculatePrice() {
        WebElement mailShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(getDriver()).moveToElement(mailShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-calc']//a[contains(text(),'Calculate a Price')]")).click();
    }

    @And("I select {string} with postcard shape")
    public void iSelectWithPostcardShape(String arg0) {
        getDriver().findElement(By.xpath("//select[@id='CountryID']/option[@value='10141']")).click();
        getDriver().findElement(By.xpath("//input[@id='option_1']")).click();
    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String arg0) {
        getDriver().findElement(By.xpath("//input[@id='quantity-0']")).sendKeys("2");
        getDriver().findElement(By.xpath("//input[@class='btn btn-pcalc btn-default']")).click();
    }

    @Then("I calculate the price and validate cost is ${double}")
    public void iCalculateThePriceAndValidateCostIs$(double d) {
        String result = getDriver().findElement(By.xpath("//div[@id='total'][contains(text(),'$2.40')]")).getText();
        System.out.println(result);
        assertThat(result).contains("2.40");

//        String result = result$.replaceAll("$", "");
//        double resultDouble = Double.parseDouble(result);
 //       System.out.println(resultDouble);
//        assertThat(resultDouble).contains(2.40);
    }






//converter temperature
    @When("I click on {string}")
    public void iClickOn(String arg0) {
        getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Temperature')]")).click();
    }

    @And("I set {string} to {string}")
    public void iSetTo(String arg0, String arg1) {
        getDriver().findElement(By.xpath("//select[@id='calFrom']//option[@value='3']")).click();
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys("54");
        getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'Celsius')]")).click();
    }

    @Then("I enter into From field {string} and verify {string} result")
    public void iEnterIntoFromFieldAndVerifyResult(String arg0, String arg1) {
        WebElement resut = getDriver().findElement(By.xpath("//b[contains(text(),'Result:')]"));
        getWait().until(ExpectedConditions.visibilityOf(resut));

        String Actualresult = getDriver().findElement(By.xpath("//div[@id='calResults']")).getText();
        assertThat(Actualresult).contains("12.2");
    }


 //  Scenario: Convert pound to kilogram
    @When("I  also click on {string}")
    public void iAlsoClickOn(String arg0) {
        getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Weight')]")).click();
    }

    @And("I  also set {string} to {string}")
    public void iAlsoSetTo(String arg0, String arg1) {
        getDriver().findElement(By.xpath("//select[@id='calFrom']//option[contains(text(),'Pound')]")).click();
        getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'Kilogram')]")).click();

    }

    @Then("I also enter into From field {string} and verify {string} result")
    public void iAlsoEnterIntoFromFieldAndVerifyResult(String arg0, String arg1) {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys("170");

//        WebElement resut = getDriver().findElement(By.xpath("//b[contains(text(),'Result:')]"));
//        getWait().until(ExpectedConditions.visibilityOf(resut));

        String Actualresult = getDriver().findElement(By.xpath("//div[@id='calResults']")).getText();
        assertThat(Actualresult).contains("77");
        System.out.println(Actualresult);
    }

//Converter mile to km
    @When("I want click on {string}")
    public void iWantClickOn(String arg0) {
        getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Length')]")).click();
    }

    @And("I want to set {string} to {string}")
    public void iWantToSetTo(String arg0, String arg1) {
        getDriver().findElement(By.xpath("//select[@id='calFrom']//option[contains(text(),'Mile')]")).click();
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys("50");
        getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'Kilometer')]")).click();
    }

    @Then("I  want  enter into From field {string} and verify {string} result")
    public void iWantEnterIntoFromFieldAndVerifyResult(String arg0, String arg1) {
        String result = getDriver().findElement(By.xpath("//div[@id='calResults']")).getText();
        assertThat(result).contains("80.4");
        System.out.println(result);
    }





    @When("I navigate to {string}")
    public void iNavigateTo(String arg0) {
        getDriver().findElement(By.xpath("//a[contains(text(),'Auto Loan Calculator')]")).click();
    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() throws InterruptedException {
        Thread.sleep(4000);
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).clear();
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).clear();
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).clear();
    }

    @And("I calculate")
    public void iCalculate() throws InterruptedException {
        getDriver().findElement(By.xpath("//body//input[3]")).click();
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String arg0) throws InterruptedException {
        //     By locator  = (By.xpath("//body/div[@id='contentout']/div[@id='content']/table/tbody/tr/td[2]"));
        //     getWait(60).until(ExpectedConditions.visibilityOfElementLocated(locator));
        //     WebElement a = getDriver().findElement(locator);

//        WebElement a = getDriver().findElement(By.xpath("//body/div[@id='contentout']/div[@id='content']/table/tbody/tr/td[2]"));
//        getWait().until(driver -> !a.isDisplayed());
        Thread.sleep(5000);
        String text1 = getDriver().findElement(By.xpath("//body/div[@id='contentout']/div[@id='content']/table/tbody/tr/td[2]")).getText();
        assertThat(text1).contains("Please provide a positive loan term value.");


    }


    @When("I search on main page for {string}")
    public void iSearchOnMainPageFor(String searchText) {
        WebElement searchLookingGlass = getDriver().findElement(By.xpath("//li[contains(@class, 'nav-search')]"));
        getActions().moveToElement(searchLookingGlass).perform();

        WebElement searchInput = getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']"));
        searchInput.sendKeys(searchText + Keys.ENTER);
    }




 //  Scenario: Quadcopters delivery
    @When("I go to {string} tab")
    public void iGoToTab(String arg0) {
        getDriver().findElement(By.xpath("//a[@class='menuitem'][contains(text(),'Help')]")).click();
    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String arg0) {
        getDriver().findElement(By.xpath("//input[@placeholder='Search for a topic']")).sendKeys("Quadcopters delivery");
        getDriver().findElement(By.xpath("//*[contains(@class, 'slds-button_brand search-button')]")).click();
    }

    @Then("I verify that no results of {string} available in help search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String arg0) {
        String result = getDriver().findElement(By.xpath("//div[@class='resultsWrapper']")).getText();
        assertThat(result).doesNotContain("Quadcopters delivery");
    }



    @When("I perform {string} search")
    public void iPerformSearch(String po) {
        WebElement search = getDriver().findElement(By.xpath("//a[contains(text(),'Search USPS.com')]"));
        WebElement searchInput = getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']"));
        WebElement searchButton = getDriver().findElement(By.xpath("//li[contains(@class,'nav-search')]//input[@value='Search']"));
        getActions()
                .moveToElement(search)
                .sendKeys(po)
                .perform();
    }


    @And("I set {string} in filters")
    public void iSetInFilters(String filter) throws InterruptedException {
        WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
        getWait().until(ExpectedConditions.invisibilityOf(spinner));
        getDriver().findElement(By.xpath("//div[@id='dyn_nav_col']//a[text()='" + filter + "']")).click();
        getWait().until(ExpectedConditions.invisibilityOf(spinner));

    }

    @Then("I also verify that {string} results found")
    public void iAlsoVerifyThatResultsFound(String count) throws InterruptedException {
        String heading = getDriver().findElement(By.xpath("//span[@id='searchResultsHeading']")).getText();
        //  assertThat(heading).contains(count);

        //replace to nothing - everything except of digits (only if you need extract 1 digit)
        String headingCount = heading.replaceAll("\\D*", "");
        //there 7 still string, take string and convert to number
        int actualCount = Integer.parseInt(headingCount);
        int expectedCount = Integer.parseInt(count);
        assertThat(actualCount).isEqualTo(expectedCount);

        // count row with results

        List<WebElement> results = getDriver().findElements(By.xpath("//*[@id='records']/li")); //find plural elements
        int resultsCount = results.size(); //get size
        assertThat(resultsCount).isEqualTo(expectedCount);
    }

    @When("I select {string} in results")
    public void iSelectInResults(String arg0) {
        getDriver().findElement(By.xpath("//span[contains(text(),'Priority Mail | USPS')]")).click();
    }

    @And("I click {string} button")
    public void iClickButton(String buttonTitle) {
        getDriver().findElement(By.xpath("//a[contains(text(),'" + buttonTitle + "')]")).click();
    }


    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() {
        String originalWindow = getDriver().getWindowHandle(); //save originalWindow

        //switch to new tab
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }
        getWait(10).until(ExpectedConditions.titleContains("Sign in"));

        WebElement username = getDriver().findElement(By.xpath("//input[@id='username']"));
        assertThat(username.isDisplayed()).isTrue();

        //closes active window
        getDriver().close();
        //switch back to original window
        getDriver().switchTo().window(originalWindow);
    }



    // Scenario: Every door direct mail
    @When("I go to {string} under {string}")
    public void iGoToUnder(String subMenu, String tab) {
        getActions().moveToElement(getDriver().findElement(By.xpath("//a[text()='" + tab + "']"))).perform();
        getDriver().findElement(By.xpath("//a[text()='" + subMenu + "']")).click();
    }

    @And("I search for {string}")
    public void iSearchFor(String adress) {
        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(adress);
        getDriver().findElement(By.xpath("//button[@class='search-form-field-icon search-form-field-icon-search']")).click();
    }

    @And("I click {string} on the map")
    public void iClickOnTheMap(String text) {
        WebElement overlay = getDriver().findElement(By.xpath("//div[@id='eddm_overlay-progress']"));
        getWait().until(ExpectedConditions.visibilityOf(overlay));
        getWait(10).until(ExpectedConditions.invisibilityOf(overlay));
        getDriver().findElement(By.xpath("//a[contains(text(),'" + text + "')]")).click();

    }

    @When("I click {string} on the table")
    public void iClickOnTheTable(String arg0) throws InterruptedException {
        Thread.sleep(5000);
        getDriver().findElement(By.xpath("//a[@class = 'totalsArea']")).click();
    }

    @And("I close modal window")
    public void iCloseModalWindow() {
        getDriver().findElement(By.xpath("//div[@id='modal-box-closeModal']")).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() {
        // initially we have not all elements
        // we need to scroll to the last element so more of them loaded (infinite scroll)

        //we get the number of total expected elements
        String totalCountString = getDriver().findElement(By.xpath("//a[contains(@class,'totalsArea')]")).getText();
        int totalCount = Integer.parseInt(totalCountString.replaceAll("\\D*", ""));

        //get locator of each table cell list
        By costListSelector = By.xpath("//td[@idx='7']");

        //find current element list on the page
        List<WebElement> costList = getDriver().findElements(costListSelector);
        //get last element from the current costList (25th element) and move mouse to it
        int lastIndex = costList.size() - 1;
        WebElement lastElementList = costList.get(lastIndex);
        //we scroll to the current last element
        getActions().moveToElement(lastElementList).perform();
        //wait until total number of elements loaded
        getWait().until(ExpectedConditions.numberOfElementsToBe(costListSelector, totalCount));

        costList = getDriver().findElements(costListSelector);
        //summarize total
        double actualTotal = 0;  //double because here decimal
        for (WebElement cost : costList){
            String costString = cost.getText().replace("$", ""); //remove $ sign from cost numbers
            double costTotal = Double.parseDouble(costString);
            actualTotal = actualTotal + costTotal;
        }
        System.out.println("Actual Total:" + actualTotal);

        String expectedTotalString = getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText();
        double expectedTotal = Double.parseDouble(expectedTotalString);
        System.out.println("Expected total: " + expectedTotal);

        // use approximate match
        assertThat(actualTotal).isCloseTo(expectedTotal, Percentage.withPercentage(1));
    }






    @Then("I verify that we can see  {string} calculator error")
    public void iVerifyThatWeCanSeeCalculatorError(String arg0) throws InterruptedException {
        Thread.sleep(5000);
        String text1 = getDriver().findElement(By.xpath("//body/div[@id='contentout']/div[@id='content']/table/tbody/tr/td[2]")).getText();
        assertThat(text1).contains("Please provide a positive loan term value.");

    }




    //Ups1
    @And("I open Shipping menu")
    public void iOpenShippingMenu()  {
        getDriver().findElement(By.xpath("//a[@class='ups-analytics ups-menu_toggle'][contains(text(),'Shipping')]")).click();
    }

    @And("I go to Create a Shipment")
    public void iGoToCreateAShipment() {
        getDriver().findElement(By.xpath("//a[contains(text(),'Create a Shipment:')]")).click();

    }

    @When("I fill out origin shipment fields")

    public void iFillOutOriginShipmentFields() {
  //      WebElement element= getDriver().findElement(By.xpath("//select[@name='countryDropdown']"));
  //      getExecutor().executeScript("arguments[0].click();", element);

  //      By locator = By.xpath("//input[@name='organization']");
  //      new WebDriverWait(getDriver(),
  //              10).until(ExpectedConditions.visibilityOfElementLocated(locator));

        getDriver().findElement(By.xpath("//option[contains(text(),'United States')]")).click();
        getDriver().findElement(By.xpath("//input[@name='organization']")).sendKeys("Administrator");

        getDriver().findElement(By.xpath("//input[@placeholder='Street Address']")).sendKeys("4970 El Camino Real");
        getDriver().findElement(By.xpath("//input[@name='postal']")).sendKeys("94022");

        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("1234567890");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("mail@example.com");
    }

    @And("I submit the shipment form")
    public void iSubmitTheShipmentForm() throws InterruptedException {
        Thread.sleep(5000);
        getDriver().findElement(By.xpath("//button[@class='close_btn_thick']")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
    }
    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() {
        String result = getDriver().findElement(By.xpath("//div[@class='ups-section']")).getText();
        assertThat(result).contains("Administrator");
        assertThat(result).contains("1234567890");
        assertThat(result).contains("mail@example.com");
        assertThat(result).contains("94022");
        assertThat(result).contains("4970 El Camino Real");
    }

    @And("I cancel the shipment form")
    public void iCancelTheShipmentForm() {
        getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']")).click();
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();
    }

    @Then("I verify shipment form is reset")
    public void iVerifyShipmentFormIsReset() {
        WebElement req = getDriver().findElement(By.xpath("//em[text()='Indicates required field']"));
      assertThat(ExpectedConditions.textToBePresentInElement(req, "Indicates required field"));
    }





 // multiple windows (13-14 QA 1:00)
    @And("I work with multiple windows")
    public void iWorkWithMultipleWindows() {
    WebElement mail = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
    getActions()
            .moveToElement(mail)
            .keyDown(Keys.COMMAND)//hold button
            .click()
            .keyUp(Keys.COMMAND) //release
            .perform();

    //switch to new window
    getDriver().getWindowHandles().forEach(h -> getDriver().switchTo().window(h));

   //save original window before you click
    Set<String> originalWindows = getDriver().getWindowHandles();
    //click on button
   getDriver().findElement(By.xpath("//a[contains(text(),'Print a Label')]")).click();
   //take new windows
   Set<String> newWindows = getDriver().getWindowHandles();
   //remove from new Windows - original windows and switch
   newWindows.removeAll(originalWindows);//subtract one set from another
   String newHandle = newWindows.iterator().next();
   getDriver().switchTo().window(newHandle);

    //switch to this window
//    getDriver().getWindowHandles().forEach(h -> getDriver().switchTo().window(h));

    getWait(10).until(ExpectedConditions.titleContains("Sign In"));

    //switch back to the first window
    getDriver().switchTo().window(getDriver().getWindowHandles().iterator().next());
    //click on track and manage
    WebElement manage = getDriver().findElement(By.xpath("//a[contains(text(),'Track & Manage')]"));
        getActions()
                .moveToElement(mail)
                .keyDown(Keys.COMMAND)//hold button
                .click()
                .keyUp(Keys.COMMAND) //release
                .perform();

        //switch to new window
        getDriver().getWindowHandles().forEach(h -> getDriver().switchTo().window(h));
        //check element visible
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("track-package--input")));
        //switch back to the first window
        getDriver().switchTo().window(getDriver().getWindowHandles().iterator().next());
    }


}
