package swaglabs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabs {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        boolean titleMatch = actualTitle.equals(expectedTitle);

        boolean isUsernameFieldPresent = false;
        try {
            WebElement usernameField = driver.findElement(By.id("user-name"));
            isUsernameFieldPresent = usernameField.isDisplayed();
        } catch (Exception e) {
            isUsernameFieldPresent = false;
        }

        boolean isPasswordFieldPresent = false;
        try {
            WebElement passwordField = driver.findElement(By.id("password"));
            isPasswordFieldPresent = passwordField.isDisplayed();
        } catch (Exception e) {
            isPasswordFieldPresent = false;
        }

        boolean isLoginButtonPresent = false;
        try {
            WebElement loginButton = driver.findElement(By.id("login-button"));
            isLoginButtonPresent = loginButton.isDisplayed();
        } catch (Exception e) {
            isLoginButtonPresent = false;
        }

        System.out.println("Actual Title: " + actualTitle);
        System.out.println("Expected Title: " + expectedTitle);
        System.out.println("Title Match: " + titleMatch);
        System.out.println("Username Field Present: " + isUsernameFieldPresent);
        System.out.println("Password Field Present: " + isPasswordFieldPresent);
        System.out.println("Login Button Present: " + isLoginButtonPresent);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_test_result.csv"))) {
            writer.write("Test Type, Actual Title, Expected Title, Title Match, Username Field Present, Password Field Present, Login Button Present\n");
            writer.write("Homepage Test, " + actualTitle + ", " + expectedTitle + ", " + titleMatch + ", " + isUsernameFieldPresent + ", " + isPasswordFieldPresent + ", " + isLoginButtonPresent + "\n");
        }

        System.out.println("Test results saved to swaglabs_test_result.csv");

        Thread.sleep(5000);
        driver.quit();
    }
}

