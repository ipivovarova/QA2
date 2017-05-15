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
import pages.delfiRu.TopArticleWrapper;

/**
 * Created by Inga on 12/05/2017.
 */
public class TestArticleFullCommentCount {
    private CommonFunctions commonFunctions = new CommonFunctions();
    private static final String WEB_SITE_URL = "http://rus.delfi.lv";
    private static final String ARTICLE_NAME = "В понедельник ожидаются дожди и грозы";
    private static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    @Test
    public void checkCommentCount() {
        LOGGER.info("This test find article by name on home page and selects comment count of this article" +
                " then go to the comment page and calculate comment count - registered and anonymous." +
                " We calculate real comments. This means that we calculate comments from the list of comments for each type.");

        commonFunctions.initWebDriver();
        commonFunctions.launchApp(WEB_SITE_URL);
        HomePage homePage = new HomePage(commonFunctions);

        LOGGER.info("Find article by name");
        TopArticleWrapper article = homePage.getArticleByName(ARTICLE_NAME);

        LOGGER.info("Getting title and comment count for fended article");
        String titleArticle = article.getArticleName();
        int countCommentFirstArticle = commonFunctions.getCountFromString(article.getArticleCommentCount());
        LOGGER.info("Article title on the selected article: " + titleArticle);
        LOGGER.info("Comment count of the article: " + countCommentFirstArticle);

        LOGGER.info("Open article page for selected article");
        ArticlePage articlePage = homePage.openArticle(article);

        LOGGER.info("Open comment page for selected article");
        CommentsPage commentsPage =  articlePage.openComments();

        LOGGER.info("Getting total count of comments (registered and anonymous)");
//        int totalComment = commentsPage.getTotalComment();
        int totalComment = commentsPage.getRealTotalComments();

        LOGGER.info("Check comments count from total registered and anonymous comments");
        Assert.assertEquals("wrong comment count on article page", countCommentFirstArticle, totalComment, 0);


    }

    /**
     * Stopping webDriver
     */
    @After
    public void closeDriver() {
        commonFunctions.shutDownDriver();
    }
}
