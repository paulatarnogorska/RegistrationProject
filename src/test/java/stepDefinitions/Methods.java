package stepDefinitions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods {

    public static String randomName() {
        Faker faker = new Faker();
        return faker.name().lastName() + faker.random().nextInt(100);
    }

    public static String randomMail() {
        Faker faker = new Faker();
        return faker.name().firstName() + "@test.com";
    }

    public static String longUserName() {
        return "bk0YeJSiuHl82m5wqIPkB1PhLG2UP9p7zEH6Aj6eehDjB0tppgwpFlzzZHytgao4VnXX0LC50yRYmdOR9dJGF1fFnKhje5l7ISqW123" ;
    }
}
