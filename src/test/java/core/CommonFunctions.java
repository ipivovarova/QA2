package core;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Inga on 25/04/2017.
 */
public class CommonFunctions {

    WebDriver webDriver;

    private static final String FIREFOX_PROPERTY_NAME = "webdriver.gecko.driver";
    private static final String FIREFOX_DRIVER_LOCATION = "C:/Soft/geckodriver.exe";
    private static final Logger LOGGER = Logger.getLogger(CommonFunctions.class);

    /*
     * constructor for class CommonFunctions
     */
    public CommonFunctions() {
        this.webDriver = webDriver;
    }

    /*
     * initialize driver.
     *
     */
    public WebDriver initWebDriver() {
        LOGGER.info("Setting chromedriver location: " + FIREFOX_DRIVER_LOCATION);
        System.setProperty(FIREFOX_PROPERTY_NAME, FIREFOX_DRIVER_LOCATION);

        LOGGER.info("Opening FireFox browser");
        this.webDriver = new FirefoxDriver();

        LOGGER.info("Maximize browser window size");
        webDriver.manage().window().maximize();

        return webDriver;
    }

    /**
     * launch web application.
     *
     * @param appHomePage - home page web application
     */
    public void launchApp(String appHomePage) {
        if(!appHomePage.contains("http://") && !appHomePage.contains("https://")) {
            appHomePage = "http://" + appHomePage;
        }
        LOGGER.info("Launch: " + appHomePage);
        webDriver.get(appHomePage);
    }

    /**
     * Method is waiting for element to be added in DOM
     *
     * @param element - element to wait
     * @param mills - max time to wait in milliseconds
     */
    public void waitForElement(By element, long mills) {
        WebDriverWait wait = new WebDriverWait(webDriver, mills);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    /*
     * Method is waiting for element to be added in DOM
     *
     * @param element - element to wait
     * @param mills - max time to wait in milliseconds
    */
    public void waitDisplayElement(final By element, long mills) {
        Boolean pageCommentsCheckStart = (new WebDriverWait(webDriver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(element).isDisplayed();
            }
        });
    }

    /**
     * Method to clock on specific element
     *
     * @param element element to click
     */
    public void click(By element) {
        webDriver.findElement(element).click();
    }

    /**
     * This method created to pause test - needs to wait for data refresh or receiving mail message
     *
     * @param mills time to wait in milliseconds
     */
    public void pause(long mills) {
        try {
            Thread.sleep(mills);
            LOGGER.info("Test pauses for " + mills + " milliseconds to wait for data");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * shut down driver.
     *
     */
    public void shutDownDriver() {
        this.webDriver.close();
        this.webDriver.quit();
    }

    /**
     * Method returns a list of elements with a specific locator
     *
     * @param element element locator to search
     * @return list of WebElements
     */
    public List<WebElement> findElements(By element) {
        return webDriver.findElements(element);
    }

    /**
     * Method returns WebElement with a specific locator
     *
     * @param element element locator to search
     * @return WebElement
     */
    public WebElement getElement(By element) {
        return webDriver.findElement(element);
    }

    /*
     * Return count from "(some_count)"
     *
     * @return comment count
    */
    public int getCountFromString(String counterString) {
        return Integer.parseInt(counterString.substring(1, counterString.length()-1));
    }

}
