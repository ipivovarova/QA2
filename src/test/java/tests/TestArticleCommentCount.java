package tests;

import core.CommonFunctions;
import firstTest.DelfiTest;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.delfiRu.ArticlePage;
import pages.delfiRu.CommentsPage;
import pages.delfiRu.HomePage;
import webDriver.Browser;

/**
 * Compare comments count from 3 sources:
 *   1. Comment count from first article from article top list
 *   2. Comment count from article title from article page
 *   3. Total comments count from comment page
 */
public class TestArticleCommentCount {

    private CommonFunctions commonFunctions = new CommonFunctions();
    private static final String WEB_SITE_URL = "http://rus.delfi.lv";
    private static final int ARTICLE_ORDER = 0;
    private static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    @Test
    public void checkCommentCount() {

        LOGGER.info("This test selects comment count of first article from home page," +
                " go to this article and select comment count from article title. Compare those two values." +
                " Then we go to comment page and calculate comment count - registered and anonymous");

        commonFunctions.initWebDriver();
        commonFunctions.launchApp(WEB_SITE_URL);
        HomePage homePage = new HomePage(commonFunctions);

        LOGGER.info("Getting title and comment count for first article");
        String titleArticle = homePage.getArticleByOrder((ARTICLE_ORDER)).getArticleName();
        int countCommentFirstArticle = commonFunctions.getCountFromString(homePage.getArticleByOrder(ARTICLE_ORDER).
                getArticleCommentCount());
        LOGGER.info("First article title on first page: " + titleArticle);
        LOGGER.info("Comment count on first page: " + countCommentFirstArticle);

        LOGGER.info("Open selected article");
        ArticlePage articlePage = homePage.openArticle(ARTICLE_ORDER);

        LOGGER.info("Getting comment count from the title on article page ");
        String titleArticlePage = articlePage.getArticleTitle();
        int countCommentArticlePage = articlePage.getCommentCount();
        LOGGER.info("Article title on article page: " + titleArticlePage);
        LOGGER.info("Comment count on article page: " + countCommentArticlePage);

        Assert.assertEquals("wrong comment count on article page", countCommentFirstArticle, countCommentArticlePage, 0);

        LOGGER.info("Comment count on first page " + countCommentFirstArticle + " equals to comment count on article page " + countCommentArticlePage);

        LOGGER.info("Moving to article comment page");
        CommentsPage commentsPage = articlePage.openComments();

        LOGGER.info("Getting comments total count (registered and anonymous)");
        int totalComment = commentsPage.getTotalComment();

        LOGGER.info("Check comments count from total registered and anonymous comments");
        Assert.assertEquals("wrong comment count on article page", countCommentArticlePage, totalComment, 0);

    }

        /**
         * Stopping webDriver
         */
        @After
        public void closeDriver() {
            commonFunctions.shutDownDriver();
        }


}
