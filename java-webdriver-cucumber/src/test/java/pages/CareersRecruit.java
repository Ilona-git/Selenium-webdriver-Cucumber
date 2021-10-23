package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static support.TestContext.*;

public class CareersRecruit extends CareersHeader {

    public CareersRecruit() {
        url = "https://skryabin-careers.herokuapp.com/recruit";
    }

//    public void refresh() {
//        getExecutor().executeScript("location.reload(true)");
//    }

    // static elements
    @FindBy(xpath = "//a[@href='/new_position']")
    private WebElement newPositionLink;

    // dynamic elements
    private WebElement positionCard(String title) {
        return getDriver().findElement(By.xpath("//h4[text()='" + title + "']/../../.."));
    }

    private WebElement closeForPosition(String title) {
        return getDriver().findElement(By.xpath("//h4[text()='" + title + "']/../../..//button"));
    }

    // micro methods
    public CareersPosition clickNewPosition() {
        click(newPositionLink);
        return new CareersPosition();
    }

    public boolean isPositionVisible(String title) {
        try {
            return positionCard(title).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public CareersRecruit removePosition(String title) {
        WebElement card = positionCard(title);
        WebElement close = closeForPosition(title);
        getActions().moveToElement(card).perform();
        getWait().until(ExpectedConditions.elementToBeClickable(close));
        click(close);
        try {
            waitForPositionDisappear(title);
        } catch (TimeoutException e) {
            click(close);
            waitForPositionDisappear(title);
        }
        return new CareersRecruit();
    }

    protected CareersRecruit waitForPositionDisappear(String title) {
        try {
            getWait().until(ExpectedConditions.invisibilityOf(positionCard(title)));
        } catch (NoSuchElementException e) {
            // expected behavior if happens
        }
        return new CareersRecruit();
    }

    protected CareersRecruit waitForPositionAppear(String title) {
        getWait().until(ExpectedConditions.visibilityOf(positionCard(title)));
        return new CareersRecruit();
    }

}