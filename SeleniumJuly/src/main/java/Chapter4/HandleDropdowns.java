package Chapter4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HandleDropdowns {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        driver.manage().window().maximize();

        WebElement dropDownState = driver.findElement(By.id("state"));
        Select stateOptions = new Select(dropDownState);

        stateOptions.selectByIndex(2);//select the 3rd option

        List<WebElement> states = stateOptions.getOptions();

        for (WebElement state : states){
            System.out.println(state.getAttribute("value"));
        }

        multiSelectDropdown();
    }

    public static void multiSelectDropdown(){
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://ironspider.ca/forms/dropdowns.htm");

        WebElement dropDownCoffee = driver.findElement(By.name("coffee"));
        Select coffeeMenu = new Select(dropDownCoffee);
        coffeeMenu.selectByValue("cream");

        WebElement dropDownCoffee2 = driver.findElement(By.name("coffee2"));
        Select coffeeOptions = new Select(dropDownCoffee2);
        coffeeOptions.selectByValue("milk2");
        coffeeOptions.selectByValue("honey");
        coffeeOptions.selectByValue("muffin");
        coffeeOptions.deselectByValue("cream");

        List<WebElement> mySelection = coffeeOptions.getAllSelectedOptions();

        System.out.println("I like my coffee with:\n");
        for (WebElement option : mySelection){
            System.out.println(option.getAttribute("text"));
        }


    }
}
