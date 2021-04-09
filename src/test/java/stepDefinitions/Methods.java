package stepDefinitions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

public class Methods {
    private WebDriver driver;

    public Methods(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\paula\\Documents\\MVT20\\Testautomation\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\paula\\Documents\\MVT20\\Testautomation\\Selenium\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(By by) {

        (new WebDriverWait(driver, 10, 1000)).
                until(ExpectedConditions.presenceOfElementLocated(by));

        driver.findElement(by).click();
    }

    public void sendKeys(By by, String keys) {

        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);
    }

    public String randomName() {
        return "Ugalinyamachomatamusana" + new Date().getTime();
    }

    public String randomMail() {
        Faker faker = new Faker();
        return faker.name().firstName() + "@test.com";
    }

    public String longUserName() {
        return "bk0YeJSiuHl82m5wqIPkB1PhLG2UP9p7zEH6Aj6eehDjB0tppgwpFlzzZHytgao4VnXX0LC50yRYmdOR9dJGF1fFnKhje5l7ISqW123";
    }
}
