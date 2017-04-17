package firstTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Inga on 02/04/2017.
 */

public class SearchElement {

    private static final String SEARCHING = "SELENIUM JAVA";

    @Test
    public void firstTest() {

        // set path to the  Firefox driver
        System.setProperty("webdriver.gecko.driver", "C:/Soft/geckodriver.exe");
        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver();
        // wait load home page
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // maximize window
        driver.manage().window().maximize();

        // open page google.com
        driver.get("http://google.com");
        // find element for input search string
        WebElement element = driver.findElement(By.id("lst-ib"));
        // click on the element (set the focus)
        element.click();
        // enter string for search
        element.sendKeys(SEARCHING);
        // submit the form
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // wait 10 sec until the element is present
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
        // compare pageTitle to input string
        Assert.assertEquals(SEARCHING, dynamicElement.getAttribute("value"));

        // Should see: "SELENIUM JAVA - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Attribute is: " + dynamicElement.getAttribute("value"));

        //Close the browser
         driver.quit();
    }
}
