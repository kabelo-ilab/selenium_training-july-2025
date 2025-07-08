package Chapter5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TheExplicitWait {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("a[href='/dynamic_loading']")).click();

        driver.findElement(By.cssSelector("a[href='/dynamic_loading/1']")).click();
        //button
        driver.findElement(By.cssSelector("div[id='start'] button")).click();

        WebElement lblHeading = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='finish'] h4")));

        System.out.println(lblHeading.getTagName());

    }
}
