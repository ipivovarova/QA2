package webDriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Inga on 14/04/2017.
 */
public class MyDriver{

    private static final Logger LOGGER = Logger.getLogger(MyDriver.class);
    private WebDriver webDriver;

    public MyDriver() {
    }

    /**
     * initialize driver.
     *
     * @param  browser
     */
    public WebDriver getDriver(final Browser browser) {

        switch (browser) {

            case Chrome :

                LOGGER.info("Opening Chrome browser");
                webDriver = new ChromeDriver();
                break;

            case FF :
                LOGGER.info("Opening FireFox browser");
                System.setProperty("webdriver.gecko.driver", "C:/Soft/geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;

            case IE :
                LOGGER.info("Opening IE browser");
                webDriver = new InternetExplorerDriver();
                break;

            default :
                LOGGER.info("Invalid Browser!!!");
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    /**
     * launch web page.
     */
    public void launchPage(String seachPage) {
        webDriver.get(seachPage);
    }

    /*
     * Close driver
     *
    */
    public void shutDownDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }

}
