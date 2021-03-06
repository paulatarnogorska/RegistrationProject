package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    WebDriver driver;
    Methods creator = null;

    @Before
    public void setup() {
        creator = new Methods("chrome");
        driver = creator.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }


    @Given("User navigates to Mailchimp website")
    public void user_navigates_to_mailchimp_website() {
        driver.get("https://login.mailchimp.com/signup/");
    }

    @And("User enters an email {string}")
    public void user_enters_an_email(String email) {
        if (email.equals("randomMail")) {
            creator.sendKeys(By.id("email"), creator.randomMail());

        } else if (email.equals("emptyMail")) {
            creator.sendKeys(By.id("email"), " ");
        }
    }

    @And("User enters a username {string}")
    public void user_enters_a_username(String username) {
        if (username.equals("randomUsername")) {
            creator.sendKeys(By.id("new_username"), creator.randomName());
        } else if (username.equals("longUsername")) {
            creator.sendKeys(By.id("new_username"), creator.longUserName());

        } else if (username.equals("existingUsername")) {
            creator.sendKeys(By.id("new_username"), "Paula123");
        }
    }

    @And("User enters a password {string}")
    public void user_enters_a_password(String password) {
        creator.sendKeys(By.id("new_password"), password);
    }

    @Given("User accepts cookies")
    public void user_accepts_cookies() {
        creator.click(By.id("onetrust-accept-btn-handler"));
    }

    @Given("User scrolls down")
    public void user_scrolls_down() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @When("User clicks on the signup button")
    public void user_clicks_on_the_signup_button() {
        creator.click(By.id("create-account"));
    }

    @Then("The output message {string} should be displayed")
    public void the_output_message_should_be_displayed(String outputMessage) {
        // Checks if there is an error message displayed
        boolean isInValid = driver.findElements(By.className("invalid-error")).size() >= 1;

        if (!isInValid && outputMessage.equals("Check your email")) {
            assertEquals(outputMessage, driver.findElement(By.cssSelector("h1[class='!margin-bottom--lv3 no-transform center-on-medium']")).getText());
        } else {
            List<WebElement> list = driver.findElements(By.className("invalid-error"));
            if (list.size() >= 2) {
                Assert.fail();
            } else {
                assertEquals(list.get(0).getText(), outputMessage);
            }
        }
    }
}