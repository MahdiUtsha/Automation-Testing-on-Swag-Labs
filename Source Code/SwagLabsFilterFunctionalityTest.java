package swaglabs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabsFilterFunctionalityTest {

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

        if (loginSuccess) {
            
            WebElement filterDropdown = driver.findElement(By.className("product_sort_container"));
            filterDropdown.click();
            Thread.sleep(2000);

            WebElement lowToHighOption = driver.findElement(By.xpath("//option[@value='lohi']"));
            lowToHighOption.click();

            Thread.sleep(2000);

            List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
            boolean isSorted = true;
            for (int i = 0; i < prices.size() - 1; i++) {
                double price1 = Double.parseDouble(prices.get(i).getText().replace("$", ""));
                double price2 = Double.parseDouble(prices.get(i + 1).getText().replace("$", ""));
                if (price1 > price2) {
                    isSorted = false;
                    break;
                }
            }

            System.out.println("Filter Applied: Price (low to high)");
            System.out.println("Products Sorted Correctly: " + isSorted);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("swaglabs_filter_test_result.csv"))) {
                writer.write("Test Type, Filter Applied, Products Sorted Correctly\n");
                writer.write("Filter Test, Price (low to high), " + isSorted + "\n");
            }

            System.out.println("Test results saved to swaglabs_filter_test_result.csv");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
