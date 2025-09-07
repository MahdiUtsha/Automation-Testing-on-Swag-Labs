package swaglabs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabsLoginFunctionalityTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com");
        
        Thread.sleep(2000);

        String username = "standard_user";
        String password = "secret_sauce";

        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        boolean loginSuccess = false;
        try {
            WebElement productsTitle = driver.findElement(By.className("title"));
            loginSuccess = productsTitle.isDisplayed();
        } catch (Exception e) {
            loginSuccess = false;
        }

        System.out.println("Login Successful: " + loginSuccess);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_login_test_result.csv"))) {
            writer.write("Test Type, Username, Password, Login Successful\n");
            writer.write("Login Test, " + username + ", " + password + ", " + loginSuccess + "\n");
        }

        System.out.println("Test results saved to swaglabs_login_test_result.csv");

        Thread.sleep(2000);
        driver.quit();
    }
}
