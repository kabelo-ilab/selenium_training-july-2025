package Chapter7;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HRMLoginWithReport {

   static WebDriver driver;
   static ExtentReports report;
   static ExtentSparkReporter rptSpark;
   boolean isPass;
   static String reportURL = "src/test/resources/Reports/Spark.html";

   @BeforeAll
   static void setup(){
       report = new ExtentReports();
       rptSpark = new ExtentSparkReporter(reportURL);
       rptSpark.config().setReportName("Test Summary Report");
       rptSpark.config().setDocumentTitle("The Spark Report");
       report.attachReporter(rptSpark);

       driver = new ChromeDriver();
       driver.manage().window().maximize();
   }

   @AfterEach
   void generateReport(TestInfo info){
       ExtentTest myTest = report.createTest(info.getDisplayName());

       try{
           Thread.sleep(3000);
           String image = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.BASE64);

           if (isPass){
               myTest.log(Status.PASS, "Test passed");
           }else{
               myTest.log(Status.FAIL,"Test failed ");
           }

           myTest.addScreenCaptureFromBase64String(image);

           driver.manage().deleteAllCookies();
       }catch (InterruptedException e){
           System.out.println(e.getMessage());
       }

   }

   @Test
   @DisplayName("TC1 - Valid Login")
   @Order(1)
   @Tag("UAT")
    void testValidLogin(){
        //Arrange
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        //Actual
        String actualURL = driver.getCurrentUrl();
        isPass = driver.getCurrentUrl().equals(expectedURL);
        //Assert
        assertEquals(expectedURL, actualURL);
    }

//    @Disabled
//    @ParameterizedTest(name = "TC1 - Valid Login - [{index}] : {arguments}")
//    @CsvSource(value = {"Admin,admin123"})
//    @Order(1)
//    void testValidLogin(String username, String password){
//        //Arrange
//        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
//
//        //WebDriver driver = new ChromeDriver();
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//
//        driver.findElement(By.name("username")).sendKeys(username);
//        driver.findElement(By.name("password")).sendKeys(password);
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//        //Actual
//        String actualURL = driver.getCurrentUrl();
//        isPass = driver.getCurrentUrl().equals(expectedURL);
//        //Assert
//        assertEquals(expectedURL, actualURL);
//    }

    @ParameterizedTest(name = "TC2 - Invalid Login - [{index}] : {arguments}")
    @CsvSource(value = {"Admin,admin12345", "Admin12,admin123", "TestAdmin,TestPassword"})
    @Order(2)
    @Tag("UAT")
    @Tag("System")
    void testInvalidLogin(String username, String password){
        //Arrange
        String expectedText = "Invalid credentials";

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text"));

        //Actual
        String actualText = errorMessage.getText();
        System.out.println(errorMessage.getText());
        //Assert
        isPass = errorMessage.getText().equals(expectedText);
        assertEquals(expectedText, actualText, "The system should display 'Invalid credentials' message");
    }

    @ParameterizedTest(name = "TC3 - Invalid Login - [{index}] : {arguments}")
    @CsvSource(value = {"Admin,admin12345", "Admin12,admin123", "TestAdmin,TestPassword"})
    @Order(3)
    @Tag("System")
    void testInvalidLoginNegative(String username, String password){
        //Arrange
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String actualURL = driver.getCurrentUrl();
        //Assert
        isPass = driver.getCurrentUrl().equals(expectedURL);
        assertEquals(expectedURL, actualURL, "The system should display 'Invalid credentials' message");
    }

    @AfterAll
    static void tearDown(){
       report.flush();

       try{
           Thread.sleep(2000);
           driver.quit();
           Desktop.getDesktop().browse(new File(reportURL).toURI());

       }catch (IOException ex){
           System.out.println(ex.getMessage());
       } catch (InterruptedException e) {
           System.out.println(e.getMessage());
       }

    }

}
