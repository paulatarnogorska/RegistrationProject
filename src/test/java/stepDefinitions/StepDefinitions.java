package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\paula\\Documents\\MVT20\\Testautomation\\Selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }


    @Given("User navigates to Mailchimp website")
    public void user_navigates_to_mailchimp_website() {
        driver.get("https://login.mailchimp.com/signup/");
    }

    @And("User enters an email {string}")
    public void user_enters_an_email(String email) throws InterruptedException {
        WebElement mail = driver.findElement(By.id("email"));
        if (email.equals("randomMail")) {
            mail.sendKeys(Methods.randomMail());
        } else if (email.equals("emptyMail")) {
            mail.sendKeys(" ");
        }
    }

    @And("User enters a username {string}")
    public void user_enters_a_username(String username) {
        WebElement name = driver.findElement(By.id("new_username"));
        if (username.equals("randomUsername")) {
            name.sendKeys(Methods.randomName());
        } else if (username.equals("longUsername")) {
            name.sendKeys(Methods.longUserName());
        } else if (username.equals("existingUsername")) {
            name.sendKeys("Paula123");
        }
    }

    @And("User enters a password {string}")
    public void user_enters_a_password(String password) {
        WebElement newPassword = driver.findElement(By.id("new_password"));
        newPassword.sendKeys(password);

    }

    @Given("User accepts cookies")
    public void user_accepts_cookies() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cookies = new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.id("onetrust-accept-btn-handler")));

        while (cookies.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(cookies)).click();
        }
    }

    @Given("User scrolls down")
    public void user_scrolls_down() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }


    @When("User clicks on the signup button")
    public void user_clicks_on_the_signup_button() {
        WebElement signup = new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.id("create-account")));
        signup.click();
    }

    @Then("The output message {string} should be displayed")
    public void the_output_message_should_be_displayed(String outputMessage) {

        String expected = "";
        String actual = "";

        if(outputMessage.equals("Check your email")) {
            expected = "Check your email";
            actual = driver.findElement(By.cssSelector("h1[class='!margin-bottom--lv3 no-transform center-on-medium']")).getText();
        } else if(outputMessage.equals("longUsername")) {
            expected = "Enter a value less than 100 characters long";
            actual = driver.findElement(By.cssSelector("span[class='invalid-error']")).getText();
        } else if (outputMessage.equals("existingUsername")) {
            expected = "Another user with this username already exists. Maybe it's your evil twin. Spooky.";
            actual = driver.findElement(By.cssSelector("span[class='invalid-error']")).getText();
        } else if(outputMessage.equals("Please enter a value")) {
            expected = "Please enter a value";
            actual = driver.findElement(By.cssSelector("span[class='invalid-error']")).getText();
        }

        assertEquals(expected, actual);
    }
}
