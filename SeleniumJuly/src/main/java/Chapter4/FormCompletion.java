package Chapter4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ISelect;

/**
 * Complete the form on {@link <a href="https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php">}
 */
public class FormCompletion {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        driver.manage().window().maximize();
        //firstname
        driver.findElement(By.id("name")).sendKeys("John Smith");
        //email
        driver.findElement(By.id("email")).sendKeys("example@gmail.com");
        //gender
        driver.findElement(By.xpath("/html/body/main/div/div/div[2]/form/div[3]/div/div/div[2]/input"))
                .click();
        //mobile
        driver.findElement(By.id("mobile")).sendKeys("0112358463");
        //dob
        WebElement dob = driver.findElement(By.id("dob"));
        dob.sendKeys("10-07-2000");
        dob.sendKeys(Keys.TAB);

        //subjects
        driver.findElement(By.id("subjects")).sendKeys("Selenium");
        ////input[@type='checkbox']
        //hobbies
        driver.findElement(By.xpath("/html/body/main/div/div/div[2]/form/div[7]/div/div/div[2]/input"))
                .click();
        //address
        driver.findElement(By.cssSelector("textarea[id='picture']")).sendKeys("123 Rivonia Road\nSandton");




    }
}
