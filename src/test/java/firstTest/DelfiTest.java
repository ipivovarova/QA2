package firstTest;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import  webDriver.*;

import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;

/**
 * Created by Inga on 09/04/2017.
 * Testing comment on Delfi
 */
public class DelfiTest {

    public static final String SEARCHPAGE = "http://rus.delfi.lv/";

    public static final String FIRSTPAGETITLE = "DELFI - Ведущий новостной портал в Латвии - DELFI";
    public static final By FIRSTARTICLE = By.xpath("//*[@id='column1-top']//*[contains(@class, 'top2012-title')]//a[@class='top2012-title']");
    public static final By TOPARTICLE = By.xpath("//*[@id='column1-top']//h3[contains(@class, 'top2012-title')]");

    public static final By HEADERARTICLE = By.xpath("//h1[@class='article-title']");
    public static final By COMMENTPAGE = By.className("comment-count");

    public static final By TOPTITLE = By.className("top2012-title");
    public static final By COUNTER = By.className("comment-count");
    public static final By COMMENTS = By.xpath("//*[contains(@class, 'comment-thread-switcher-list-a ')]");
    public static final By COMMENTREGLIST = By.xpath("//*[@id='comments-list']//div[@data-post-id]");

    public static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    public WebDriver driver;
    public static final String FFPROPRTYNAME = "webdriver.gecko.driver";
    public static final String FFPROPERTYVALUE = "C:/Soft/geckodriver.exe";


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

        LOGGER.info("Wait while load the page with first article");
        waitLoadPage(FIRSTPAGETITLE);

        LOGGER.info("Getting comment count for first article");
        String titleArticle = getTitleArticle(driver);
        int countCommentFirstArticle = getCommentCount(driver);
        LOGGER.info("First article title on first page: "+ titleArticle);
        LOGGER.info("Comment count on first page: "+ countCommentFirstArticle);

        LOGGER.info("Open first article");
        driver.findElement(FIRSTARTICLE).click();

        LOGGER.info("Wait while load the page with first article");
        waitLoadPage(COUNTER);

        LOGGER.info("Getting comment count from the title on article page ");
        int countCommentArticlePage = getCommentCount(driver);
        LOGGER.info("Comment count on article page in: " + countCommentArticlePage);

        Assert.assertEquals("wrong comment count on article page", countCommentFirstArticle, countCommentArticlePage, 0);

        LOGGER.info("Comment count on first page " + countCommentFirstArticle + " equals to comment count on article page "+ countCommentArticlePage);

        LOGGER.info("Moving to article comment page");
        driver.findElement(COUNTER).click();

        LOGGER.info("Wait while load the page Comments");
        waitLoadPage(By.id("comments-listing"));

        LOGGER.info("Getting comments total count (registered and anonymous)");
        int totalComment = getTotalComment(driver);

        LOGGER.info("Check comments count from total registered and anonymous comments");
        Assert.assertEquals("wrong comment count on article page", countCommentArticlePage, totalComment, 0);

        //LOGGER.info("Getting registered users comment count");
        //int countCommentFromList = getCommentCountFromList(driver);

        //LOGGER.info("Check comments count from comments list");
        //Assert.assertEquals("wrong comment count on article page", countCommentArticlePage, countCommentFromList, 0);

        LOGGER.info("Comment count on article and first pages are corrects!");

        LOGGER.info("We are closing our browser");
        shutDownDriver(driver);

    }

    /*
     * Create driver and maximase window
     *
     * @return - object driver
     */
    public WebDriver getDriver() {
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
    public void shutDownDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }

    /*
     * Wait while load the page
     *
    */
    public void waitLoadPage(final By waitingElement) {
        Boolean pageCommentsCheckStart = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(waitingElement).isDisplayed();
            }
        });
    }

    public void waitLoadPage(final String waitingPageTitle) {
        Boolean pageCommentsCheckStart = (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getTitle().contains(waitingPageTitle);
            }
        });
    }


    /*
     * Return comment count of article
     *
     * @return - comment count
     */
    public int getCommentCount(WebDriver driver) {
        int count = 0;
        try {
            WebElement counter = driver.findElement(COUNTER);
            count = getCountFromString(counter.getText());
        }
        catch (Exception e) {
            // Do nothing
        }
        LOGGER.info("Article comment info in:" + count);
        return count;
    }

    /*
     * Return count from "(some_count)"
     *
     * @return comment count
     */
    public int getCountFromString(String counterString) {
        return Integer.parseInt(counterString.substring(1, counterString.length()-1));
    }

    private String getTitleArticle(WebDriver driver) {
        String titleArticle = "";
        try {
            WebElement headerArticle = driver.findElement(FIRSTARTICLE);
            titleArticle = headerArticle.getText();
        }
        catch (Exception e) {
            // Do nothing
        }
        LOGGER.info("Title of article is: " + titleArticle);
        return titleArticle;
    }

    /*
      * Return total comment from buttons (registered and anonymous)
      *
      * @return total comment
     */
    private int getTotalComment(WebDriver driver) {
        int totalCount = 0;
        String buttonTitle = "";
        String commentType = "";
        int posCount = -1;
        String commentCountTitle = "";

        LOGGER.info("Calculate total comments: registered and anonymous");
        List<WebElement> commentButtons = driver.findElements(COMMENTS);
        LOGGER.info("Size: " + commentButtons.size());

        for (WebElement e : commentButtons) {
            buttonTitle = e.getText();
            LOGGER.info("Button title: " + buttonTitle);
            if (!buttonTitle.isEmpty()) {
                posCount = buttonTitle.indexOf("(");
                if (posCount >= 0) {
                    commentType = buttonTitle.substring(0, posCount - 1).trim();
                }
                commentCountTitle = buttonTitle.substring(posCount).trim();
                totalCount = totalCount + getCountFromString(commentCountTitle);
                LOGGER.info("Comment type: " + commentType + ". Comment count: " + commentCountTitle);
            }
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
