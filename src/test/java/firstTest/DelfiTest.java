package firstTest;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import  webDriver.*;

import java.util.List;

/**
 * Created by Inga on 09/04/2017.
 * Testing comment on Delfi
 */
public class DelfiTest {

    private static final String SEARCHPAGE = "http://rus.delfi.lv/";
    private static final By FIRSTARTICLE = By.xpath("//*[@id='column1-top']//*[contains(@class, 'top2012-title')]//a[1]");

    //private static final By COMMENTPAGE = By.xpath("//*[@id='article']//*[contains(@class, 'comment-count')]");
    //private static final By COMMENTPAGE = By.className("comment-add-form-listing-url comment-add-form-listing-url-registered");
    private static final By COMMENTPAGE = By.className("comment-count");

    private static final By COMMENTS = By.xpath("//*[contains(@class, 'comment-thread-switcher-list-a')]");
    //private static final By COMMENTS = By.xpath("//*[@id='comments-listing']/div[3]/a[1]");


    private static final By COUNTER = By.className("comment-count");
    private static final By COMMENTREGLIST = By.xpath("//*[@id='comments-list']//div[@data-post-id]");

    private static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    private WebDriver driver;
    private static final String FFPROPRTYNAME = "webdriver.gecko.driver";
    private static final String FFPROPERTYVALUE = "C:/Soft/geckodriver.exe";

    //private int commentCount;

    /*
        This test will test comment: count on main page and article page
     */
    @Test
    public void commentTesting() {

        Browser fireFox = Browser.IE;
        LOGGER.info("We are staring our test");
        driver =  getDriver();

        LOGGER.info("We are opening " + SEARCHPAGE);
        driver.get(SEARCHPAGE);


        LOGGER.info("Getting comment count for first article");
        int countCommentFirstArticle = getCommentCount(driver);
        LOGGER.info("Comment count on first page: "+ countCommentFirstArticle);

        LOGGER.info("Open first article");
        driver.findElement(FIRSTARTICLE).click();

        LOGGER.info("Getting comment count from the title on article page ");
        int countCommentArticlePage = getCommentCount(driver);
        LOGGER.info("Comment count on article page in: " + countCommentArticlePage);

        Assert.assertEquals("wrong comment count on article page", countCommentFirstArticle, countCommentArticlePage, 0);

        LOGGER.info("Comment count on first page " + countCommentFirstArticle + " equals to comment count on article page "+ countCommentArticlePage);

        LOGGER.info("Moving to article comment page");
        driver.findElement(COUNTER).click();

        String test = driver.findElement(COMMENTS).getText();
        LOGGER.info("Test string: " + test);

        LOGGER.info("Getting comments total count (registered and anonymous)");
        int totalComment = getTotalComment(driver);

//        LOGGER.info("Getting registered users comment count");
//        int countCommentFromList = getCommentCountFromList(driver);

//        LOGGER.info("Check comments count from comments list");
//        Assert.assertEquals("wrong comment count on article page", countCommentArticlePage, countCommentFromList, 0);

//        LOGGER.info("Comment count on article and first pages are corrects!");

//        LOGGER.info("We are closing our browser");
//        shutDownDriver(driver);

    }

    /*
     * Create driver and maximase window
     *
     * @return - object driver
     */
    private WebDriver getDriver() {
        LOGGER.info("We are initializing driver");
        System.setProperty(FFPROPRTYNAME, FFPROPERTYVALUE);

        LOGGER.info("Opening FireFox browser");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /*
     * Close driver
     *
    */
    private void shutDownDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }

    /*
     * Return comment count of article
     *
     * @return - comment count
     */
    private int getCommentCount(WebDriver driver) {
        WebElement counter = driver.findElement(COUNTER);
        int count = getCountFromString(counter.getText());
        LOGGER.info("Article comment info in:" + count);
        return count;
    }

    /*
     * Return count from "(some_count)"
     *
     * @return comment count
     */
    private int getCountFromString(String counterString) {
        int count = Integer.parseInt(counterString.substring(1, counterString.length()-1));
        LOGGER.info("Comment count in:" + count);
        return count;
    }

    /*
      * Return total comment from buttons (registered and anonymous)
      *
      * @return total comment
     */
    private int getTotalComment(WebDriver driver) {
        int totalCount = 0;
        String buttonTitle = "";

        LOGGER.info("Calculate total comments: registered and anonymous");
        List<WebElement> commentButton = driver.findElements(COMMENTS);
        LOGGER.info("Size: " + commentButton.size());

        for (WebElement e : commentButton) {
            buttonTitle = e.getText();
            e.findElement(By.xpath("//span"));
            totalCount = totalCount + getCountFromString(e.getText());
            LOGGER.info("Comment type: " + buttonTitle + ". Comment count: " + totalCount);
        }
        LOGGER.info("Total comment: " + totalCount);
        return totalCount;
    }

    /*
     * Return comment count from comment list
     *
     * @return - comment count
    */
    private int getCommentCountFromList(WebDriver driver) {
        int count = driver.findElements(COMMENTREGLIST).size();
        LOGGER.info("Comment count on comment page in:" + count);
        return count;
    }

}
