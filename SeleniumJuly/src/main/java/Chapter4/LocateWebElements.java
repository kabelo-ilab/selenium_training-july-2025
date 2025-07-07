package Chapter4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class LocateWebElements {
    public static void main(String[] args) {


        locateByName();
    }

    public static void locateByName(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        driver.manage().window().maximize();
        //locate / find the element
        WebElement txtFirstname = driver.findElement(By.name("name"));
        txtFirstname.sendKeys("Tom Smith");

    }

    public static void locateByXPath(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        //locate / find the element
        WebElement linkLogin = driver.findElement(By.xpath("//a[@href='/login']"));
        //click
        linkLogin.click();

        WebElement txtUsername = driver.findElement(By.xpath("//input[@name='username' and @id='username']"));
        txtUsername.sendKeys("tomsmith");

        WebElement txtPassword = driver.findElement(By.xpath("//input[@name='password']"));
        txtPassword.sendKeys("SuperSecretPassword!");

        WebElement btnLogin = driver.findElement(By.xpath("//button"));
        btnLogin.click();
    }

    public static void locateByAbsXPath(){
        WebDriver driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        //locate / find the element
        WebElement linkLogin = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[21]/a"));
        //click
        linkLogin.click();

        WebElement txtUsername = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div[1]/div/input"));
        txtUsername.sendKeys("tomsmith");

        WebElement txtPassword = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div[2]/div/input"));
        txtPassword.sendKeys("SuperSecretPassword!");

        WebElement btnLogin = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        btnLogin.click();
    }

    public static void locateByCss(){
        WebDriver driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        //locate / find the element
        WebElement linkLogin = driver.findElement(By.cssSelector("a[href='/login']"));
        //click
        linkLogin.click();

        WebElement txtUsername = driver.findElement(By.cssSelector("input[id='username']"));
        txtUsername.sendKeys("tomsmith");

        WebElement txtPassword = driver.findElement(By.cssSelector("input[id='password']"));
        txtPassword.sendKeys("SuperSecretPassword!");

        WebElement btnLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        btnLogin.click();

    }
}
