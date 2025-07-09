package Chapter7;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class CaptureScreenShots {
    public static void main(String[] args) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try{
            Thread.sleep(3000);
            //Take a screenshot
            File sourceFile = ((TakesScreenshot) (driver))
                    .getScreenshotAs(OutputType.FILE);
            String imageName = "_" + timeStamp + ".png"; //Test image 20250709_142136
            File destinationFile = new File("src/main/resources/Screenshots/Test image" + imageName);
            Files.move(sourceFile, destinationFile);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

    }
}
