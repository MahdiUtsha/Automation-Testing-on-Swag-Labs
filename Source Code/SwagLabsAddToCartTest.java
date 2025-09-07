package swaglabs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabsAddToCartTest {

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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_add_to_cart_test_result.csv"))) {
                writer.write("Test Type, Product Added to Cart\n");
                writer.write("Add to Cart Test, " + isProductAdded + "\n");
            }
            System.out.println("Test results saved to swaglabs_add_to_cart_test_result.csv");

            WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
            cartIcon.click();

            Thread.sleep(2000);

            WebElement cartProduct = driver.findElement(By.className("cart_item"));
            boolean isProductInCart = cartProduct.isDisplayed();

            System.out.println("Product in Cart: " + isProductInCart);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_cart_check_test_result.csv"))) {
                writer.write("Test Type, Product in Cart\n");
                writer.write("Cart Check Test, " + isProductInCart + "\n");
            }
            System.out.println("Cart check test results saved to swaglabs_cart_check_test_result.csv");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
