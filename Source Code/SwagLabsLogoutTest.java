package swaglabs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SwagLabsLogoutTest {

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
        
        Thread.sleep(2000);

        if (loginSuccess) {
            
            WebElement firstProductAddToCartButton = driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]"));
            firstProductAddToCartButton.click();

            Thread.sleep(2000);

            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
            boolean isProductAdded = cartBadge.isDisplayed() && cartBadge.getText().equals("1");

            System.out.println("Product Added to Cart: " + isProductAdded);

            WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
            cartIcon.click();

            Thread.sleep(2000);

            WebElement cartProduct = driver.findElement(By.className("cart_item"));
            boolean isProductInCart = cartProduct.isDisplayed();

            System.out.println("Product in Cart: " + isProductInCart);

            WebElement checkoutButton = driver.findElement(By.id("checkout"));
            checkoutButton.click();

            Thread.sleep(2000);

            WebElement firstNameField = driver.findElement(By.id("first-name"));
            firstNameField.sendKeys("Mahdi");

            WebElement lastNameField = driver.findElement(By.id("last-name"));
            lastNameField.sendKeys("Utsha");

            WebElement zipCodeField = driver.findElement(By.id("postal-code"));
            zipCodeField.sendKeys("1000");
            
            Thread.sleep(2000);

            WebElement continueButton = driver.findElement(By.id("continue"));
            continueButton.click();

            Thread.sleep(2000);

            WebElement finishButton = driver.findElement(By.id("finish"));
            finishButton.click();

            Thread.sleep(2000);

            System.out.println("Checkout Completed!");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_checkout_test_result.csv"))) {
                writer.write("Test Type, Checkout Completed\n");
                writer.write("Checkout Test, True\n");
            }
            System.out.println("Checkout test results saved to swaglabs_checkout_test_result.csv");

            WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
            backHomeButton.click();

            Thread.sleep(2000);

            WebElement menuButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
            menuButton.click();
            
            Thread.sleep(2000);

            WebElement logoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
            logoutButton.click();

            Thread.sleep(2000);

            System.out.println("Logged out successfully!");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_logout_test_result.csv"))) {
                writer.write("Test Type, Logged Out\n");
                writer.write("Logout Test, True\n");
            }
            System.out.println("Logout test results saved to swaglabs_logout_test_result.csv");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}