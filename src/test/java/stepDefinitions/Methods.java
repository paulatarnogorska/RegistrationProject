package stepDefinitions;

import com.github.javafaker.Faker;

public class Methods {
    public static String randomName() {
        Faker faker = new Faker();
        return faker.name().lastName() + faker.random().nextInt(100);
    }

    public static String randomMail() {
        Faker faker = new Faker();
        return faker.name().firstName() + "@test.com";
    }

    public static String longName() {
        String longname = "bk0YeJSiuHl82m5wqIPkB1PhLG2UP9p7zEH6Aj6eehDjB0tppgwpFlzzZHytgao4VnXX0LC50yRYmdOR9dJGF1fFnKhje5l7ISqW123";
        return longname;

    }
}
