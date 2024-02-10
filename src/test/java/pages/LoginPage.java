package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage
{
    WebDriver driver;
//    WebElement hello = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span"));
//    WebElement mobileNumber = driver.findElement(By.id("ap_email"));
//    WebElement continueBtn =driver.findElement(By.id("continue"));
//    WebElement password= driver.findElement(By.id("ap_password"));
//
//    WebElement signIn=driver.findElement(By.id("signInSubmit"));
    @FindBy(xpath = "//*[@id=\"nav-link-accountList\"]/span")
    private WebElement hello;

    @FindBy(id = "ap_email")
    private WebElement mobileNumber;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "ap_password")
    private WebElement password;

    @FindBy(id = "signInSubmit")
    private WebElement signIn;
    @FindBy(xpath = "//span[contains(text(),'Sign Out')]")
    private WebElement signoutButton;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickHello(){
        hello.click();
    }

    public void enterMobileNo(String mobileNo){
        mobileNumber.sendKeys(mobileNo);
    }

    public void clickContinue(){
        continueBtn.click();
    }
    public void enterPassword(String pass){
        password.sendKeys(pass);
    }
    public void clickSignIn(){
        signIn.click();
    }

    public void logout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(signIn).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(signoutButton)).click();
    }

}
