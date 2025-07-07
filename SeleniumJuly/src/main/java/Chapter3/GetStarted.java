package Chapter3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class GetStarted {
    public static void main(String[] args) {
        //instantiate a webdriver object
        WebDriver driver = new EdgeDriver();
        //open a URL
        driver.get("https://www.ilabquality.com/");
        //Maximise the window
        driver.manage().window().maximize();

        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Window ID: " + driver.getWindowHandle());

        System.out.println("=================================");

        //Open a new tab in the same window
        driver.switchTo().newWindow(WindowType.TAB);

        driver.navigate().to("https://www.selenium.dev/");

        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Window ID: " + driver.getWindowHandle());

       //driver.navigate().back();

    }
}
