package tests;

import core.CommonFunctions;
import firstTest.DelfiTest;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import pages.delfiRu.HomePage;

/**
 * Created by Inga on 12/05/2017.
 */
public class TestArticleFullCommentCount {
    private CommonFunctions commonFunctions = new CommonFunctions();
    private static final String WEB_SITE_URL = "http://rus.delfi.lv";
    private static final String ARTICLE_NAME = "This is name of the article";
    private static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    @Test
    public void checkCommentCount() {
        LOGGER.info("This test find article by name on home page and selects comment count of this article" +
                " then go to the comment page and calculate comment count - registered and anonymous." +
                " We calculate real comments. This means that we calculate comments from the list of comments for each type.");

        commonFunctions.initWebDriver();
        commonFunctions.launchApp(WEB_SITE_URL);
        HomePage homePage = new HomePage(commonFunctions);

    }

    /**
     * Stopping webDriver
     */
    @After
    public void closeDriver() {
        commonFunctions.shutDownDriver();
    }
}
