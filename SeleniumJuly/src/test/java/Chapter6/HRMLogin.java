package Chapter6;

import net.bytebuddy.description.modifier.MethodArguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HRMLogin {

   static WebDriver driver;

   @BeforeAll
   static void setup(){
       driver = new ChromeDriver();
       driver.manage().window().maximize();
   }

    @ParameterizedTest(name = "TC1 - Valid Login - [{index}] : {arguments}")
    @CsvSource(value = {"kabelo1234,kabelo1234"})
    @Order(1)
    void testValidLogin(String username, String password){
        //Arrange
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        //WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        //Actual
        String actualURL = driver.getCurrentUrl();
        //Assert
        assertEquals(expectedURL, actualURL);
    }

    @ParameterizedTest(name = "TC2 - Invalid Login - [{index}] : {arguments}")
    @CsvSource(value = {"Admin,admin12345", "Admin12,admin123", "TestAdmin,TestPassword"})
    @Order(2)
    void testInvalidLogin(String username, String password){
        //Arrange
        String expectedText = "Invalid credentials";

        //WebDriver driver = new ChromeDriver();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text"));

        //Actual
        String actualText = errorMessage.getText();
        System.out.println(errorMessage.getText());
        //Assert
        assertEquals(expectedText, actualText, "The system should display 'Invalid credentials' message");
    }

    @ParameterizedTest(name = "TC3 - Invalid Login - [{index}] : {arguments}")
    @CsvSource(value = {"Admin,admin12345", "Admin12,admin123", "TestAdmin,TestPassword"})
    @Order(3)
    void testInvalidLoginNegative(String username, String password){
        //Arrange
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

       // WebDriver driver = new ChromeDriver();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String actualURL = driver.getCurrentUrl();
        //Assert
        assertEquals(expectedURL, actualURL, "The system should display 'Invalid credentials' message");
    }


}
