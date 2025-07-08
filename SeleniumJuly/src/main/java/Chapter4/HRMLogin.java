package Chapter4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Automate the login on {@link https://opensource-demo.orangehrmlive.com/}
 * This code throws an exception {@code NoSuchElementException}
 */
public class HRMLogin {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();


            driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

    }
}
