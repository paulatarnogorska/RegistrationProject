package stepDefinitions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods {

    public WebDriver createWebDriver(String browser) {
        WebDriver driver = null;

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\paula\\Documents\\MVT20\\Testautomation\\Selenium\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\paula\\Documents\\MVT20\\Testautomation\\Selenium\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        return driver;
    }

    public static void click(WebDriver driver, By by) {

        (new WebDriverWait(driver, 10, 1000)).
                until(ExpectedConditions.presenceOfElementLocated(by));

        driver.findElement(by).click();
    }

    public static void sendKeys(WebDriver driver, By by, String keys) {

        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);
    }

    public static String randomName() {
        Faker faker = new Faker();
        return faker.name().lastName() + faker.random().nextInt(100);
    }

    public static String randomMail() {
        Faker faker = new Faker();
        return faker.name().firstName() + "@test.com";
    }

    public static String longUserName() {
        return "bk0YeJSiuHl82m5wqIPkB1PhLG2UP9p7zEH6Aj6eehDjB0tppgwpFlzzZHytgao4VnXX0LC50yRYmdOR9dJGF1fFnKhje5l7ISqW123";
    }
}
