package Chapter4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Complete the form on {@link https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php}
 */
public class FormCompletion {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        driver.manage().window().maximize();

        WebElement dateOfBirth = driver.findElement(By.xpath("//input[@name='dob' and @type='date']"));
        dateOfBirth.sendKeys("10-Jun-2015");

        String name = "Kabelo";
    }
}
