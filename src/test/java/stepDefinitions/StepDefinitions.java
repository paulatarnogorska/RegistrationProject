package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\paula\\Documents\\MVT20\\Testautomation\\Selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

   /* @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }*/

    @Given("User navigates to Mailchimp website")
    public void user_navigates_to_mailchimp_website() {
        driver.get("https://login.mailchimp.com/signup/");
    }

    @And("User accepts cookies")
    public void user_accept_cookies() {
        By cookies = By.id("onetrust-accept-btn-handler");
        System.out.println("sasas");
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(cookies)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookies));
    }

    @And("User enters an email {string}")
    public void user_enters_an_email(String email) {
        WebElement mail = driver.findElement(By.id("email"));
        if (email.equals("randomMail")) {
            mail.sendKeys(Methods.randomMail());
        }
        if (email.equals("emptyMail")) {
            mail.sendKeys(" ");
        }
    }

    @And("User enters a username {string}")
    public void user_enters_a_username(String username) {
        WebElement name = driver.findElement(By.id("new_username"));
        if (username.equals("randomUsername")) {
            name.sendKeys(Methods.randomName());
        }
        if (username.equals("longUsername")) {
            name.sendKeys(Methods.longName());
        }
        if (username.equals("existingUsername")) {
            name.sendKeys("Paula123");
        }
    }

    @And("User enters a password {string}")
    public void user_enters_a_password(String password) {
        WebElement newPassword = driver.findElement(By.id("new_password"));
        newPassword.sendKeys(password);

    }

    @When("User clicks on the signup button")
    public void user_clicks_on_the_signup_button() {
        WebElement signup = driver.findElement(By.id("create-account"));
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(signup)).click();

    }

    @Then("The output message {string} should be displayed")
    public void the_output_message_should_be_displayed(String outputMessage) {

            /*WebElement successfulLogin = driver.findElement(By.cssSelector("h1[class='!margin-bottom--lv3 no-transform center-on-medium']"));
            Assert.assertEquals(successfulLogin.getText(), outputMessage);*/

        WebElement errorInvalid = driver.findElement(By.cssSelector("span[class='invalid-error']"));
        Assert.assertEquals(outputMessage, errorInvalid.getText());
    }
}
