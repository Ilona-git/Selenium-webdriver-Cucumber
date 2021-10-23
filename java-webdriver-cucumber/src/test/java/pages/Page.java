package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.Loggable;

import java.util.logging.Level;

import static support.TestContext.*;

public abstract class Page implements Loggable {

    protected String url;
    protected String title;

    public Page() {

        PageFactory.initElements(getDriver(), this);
    }

    public void open() {
        getDriver().get(url);
    }

    public boolean areErrorsPresent() {
        LogEntries logEntries = getDriver().manage().logs().get("browser");
        for (LogEntry logEntry : logEntries) {
            if (logEntry.getLevel().equals(Level.SEVERE)) {
                getLogger().error(logEntry);
                return true;
            }
        }
        return false;
    }

    public void refresh() {
        getDriver().navigate().refresh();
    }

    public void click(WebElement element) {
        waitingToBeClickable(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            getLogger().error("click failed for " + element + " clicking with js..");
            jsClick(element);
        }
    }

    public void fill(WebElement element, String value) {
        waitingToBeVisible(element);
        String currentValue = element.getAttribute("value");
        if (!currentValue.isEmpty()) {
            element.clear();
        }
        element.sendKeys(value);
    }

    public void waitingToBeVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void waitingToBeClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void jsClick(WebElement element) {
        getExecutor().executeScript("arguments[0].click();", element);
    }




}