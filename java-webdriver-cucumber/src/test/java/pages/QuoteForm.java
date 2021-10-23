package pages;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;

        import static support.TestContext.getDriver;
        import static support.TestContext.getExecutor;

public class QuoteForm extends Page {

    public QuoteForm() {
        url = "https://skryabin.com/market/quote.html";
        title = "Get a Quote";
    }

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(name = "agreedToPrivacyPolicy")
    private WebElement privacy;

    @FindBy(xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;


    @FindBy(id = "name") //if there are id or name you can simply use it instead of xpath
    private WebElement name;
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstName;
    @FindBy(name = "middleName")
    private WebElement middleName;
    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastName;
    @FindBy(xpath = "//span[text()='Save']")
    private WebElement saveButton;


    private WebElement errorElement(String fieldName){
        return  getDriver().findElement(By.id(fieldName + "-error"));
    }
    public String getErrorText(String fieldName) {
        return errorElement(fieldName).getText();
    }

    public boolean isErrorDisplayed(String fieldName) {
        return errorElement(fieldName).isDisplayed();
    }


    public void nameFieldGetAttribute(String a){
        String namefield = getDriver().findElement(By.xpath("//input[@value='" + a + "']")).getAttribute("value");
    }


    public void fillName(String first, String last) {
        name.click();
        firstName.sendKeys(first);
        lastName.sendKeys(last);
        saveButton.click();

    }

    public void fillFirstMiddleLastName (String first, String middle, String last){
        name.click();
        firstName.sendKeys(first);
        middleName.sendKeys(middle);
        lastName.sendKeys(last);
        saveButton.click();
    }

    public void clickAgreedPrivacy(){
        privacy.click();
    }

    public void JavaClickAgreedPrivacy() {
        //  WebElement privacyPolicy = getDriver().findElement(By.name("agreedToPrivacyPolicy"));
        WebElement privacyPolicy = getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']"));
        getExecutor().executeScript("arguments[0].click();", privacyPolicy);
    }



    public void fillPassword(String value){
        password.sendKeys(value);
    }

    public void fillConfirmPassword(String value){
        confirmPassword.sendKeys(value);
    }

    public void fillBothPasswordsFields(String value){
        password.sendKeys(value);
        confirmPassword.sendKeys(value);
    }

    public void fillUsername(String value) {
        username.sendKeys(value);
    }
    public void fillEmail(String value) {
        email.sendKeys(value);
    }
    public void clickSubmit() { submitButton.click(); }

    public void clearName(){
        name.clear();
    }
    public void clearFirstName(){
        firstName.clear();
    }
    public void clearLastName(){
        lastName.clear();
    }
    public void clearUsername(){
        username.clear();
    }
    public void clearPassword(){
        password.clear();
    }
    public void clearConfirmPassword() {
        confirmPassword.clear();
    }
    public void clearEmail(){
        email.clear();
    }
}