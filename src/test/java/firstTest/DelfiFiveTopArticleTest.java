package firstTest;

import webDriver.Browser;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Inga on 23/04/2017.
 */
public class DelfiFiveTopArticleTest extends DelfiTest{

    private static final int COUNTTOPARTICLE = 5;
    private static WebElement topArticle;

    private static String titleArticle = "";
    private static String commentCount = "";
    private static int iCommentCount;

    private static final String MSEARCHPAGE = "http://m.rus.delfi.lv/";
    private static final By MTOPARTICLE = By.className("md-mosaic-title");
    private static final By MTOPTITLE = By.className("md-scrollpos");
    private static final By MCOUNTER = By.className("commentCount");
    private static final By MTOPSEARCHPAGE = By.className("md-block-title");


    private static final By FOOTERSEARCHPAGE = By.className("top2012-title");

    /*
        This test will test comment: count on main page and article page
     */
    @Test
    public void fiveArticleCommentTesting() {

        Browser fireFox = Browser.IE;
        LOGGER.info("We are staring our test");
        driver = getDriver();

        driver = openSearchPage(driver, SEARCHPAGE, FOOTERSEARCHPAGE);

        HashMap<String, Integer> topListArticleD = getMetaTopArticle(driver, TOPARTICLE, TOPTITLE, COUNTER, COUNTTOPARTICLE);

        // http://m.rus.delfi.lv/
        driver = openSearchPage(driver, MSEARCHPAGE, MTOPSEARCHPAGE);

        HashMap<String, Integer> topListArticleM = getMetaTopArticle(driver, MTOPARTICLE, MTOPTITLE, MCOUNTER, COUNTTOPARTICLE);

        LOGGER.info("Compare two articles list");
        Assert.assertEquals("wrong article list", topListArticleD, topListArticleM);

        LOGGER.info("We are closing our browser");
        shutDownDriver(driver);

    }

    public WebDriver openSearchPage(WebDriver driver, String searchPage, By bottomPageEl) {

        LOGGER.info("We are opening " + searchPage);
        driver.get(searchPage);

        LOGGER.info("Wait while load the page with first article");
        waitLoadPage(bottomPageEl);
        return driver;
    }

    public HashMap<String, Integer> getMetaTopArticle(WebDriver driver, By byTopArticle, By byTopTitle, By byTopCounter, int countArticle) {

        List<WebElement> topArticles = driver.findElements(byTopArticle);
        LOGGER.info("Count of top articles: " + topArticles.size());

        HashMap<String, Integer> hmap = new HashMap<String, Integer>();

        for (int i=0; i < countArticle; i++) {
            topArticle = topArticles.get(i);
            titleArticle = topArticle.findElement(byTopTitle).getText();
            iCommentCount = getCountFromString(topArticle.findElement(byTopCounter).getText());

            hmap.put(titleArticle, Integer.valueOf(iCommentCount));
            LOGGER.info("Title of article: " + titleArticle);
            LOGGER.info("Count of comment: " + iCommentCount);
        }

        return hmap;
    }
}
