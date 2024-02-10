package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.SearchPage;
import pages.SelectedItem;

import java.util.ArrayList;

public class Index {

    public WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public void testHomePage(){
        driver.get("https://amazon.in/");
    }

    @Test(priority = 1)
    public void testLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.clickHello();
        loginPage.enterMobileNo("9608621099");
        loginPage.clickContinue();
        loginPage.enterPassword("Papa123#");
        loginPage.clickSignIn();

    }

    @Test(priority = 2)
    public void searchItems() throws InterruptedException {
        SearchPage searchPage= new SearchPage(driver);
        String searchItem= "Samsung";
        searchPage.enterText(searchItem);
        searchPage.clickSearchBar();
        searchPage.verifySearchedProduct(searchItem);
        searchPage.selectSearchItem();
    }

    @Test(priority = 3)
    public void selectQuentity() throws Exception {
        SelectedItem selectedItem=new SelectedItem(driver);
        selectedItem.buyTheItem();
    }

    @Test(priority = 4)
    public void validateCheckout() throws InterruptedException {
        Thread.sleep(3000);
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        checkoutPage.checkTextPage();
        checkoutPage.checkCardOption();
    }

    @Test(priority = 5, dependsOnMethods = "testLogin")
    public void logOut() {
        LoginPage loginPage = new LoginPage(driver);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        loginPage.logout();
    }

    @AfterClass
    public void closeDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
