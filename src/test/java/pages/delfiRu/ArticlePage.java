package pages.delfiRu;

import core.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Article page.
 */
public class ArticlePage {
    private final CommonFunctions commonFunctions;
    public static final By COUNTER = By.className("comment-count");
    private static final long WAIT_MILL = 30;
    private static final By HEADER_ARTICLE = By.xpath("//*h1[@class='article-title']");
    private static final By TITLE_ARTICLE = By.xpath("./span");
    private static final By COMMENT_COUNT = By.xpath("./a[@class='comment-count']");
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    /*
     * constructor
     */
    public ArticlePage(CommonFunctions commonFunctions) {
        this.commonFunctions = commonFunctions;
        commonFunctions.waitDisplayElement(COUNTER, WAIT_MILL);
        LOGGER.info("Article page loaded");
    }

    public WebElement findArticleHeader() {
        return commonFunctions.getElement(HEADER_ARTICLE);
    }

    public WebElement findArticleCommentCount() {
        return findArticleHeader().findElement(COMMENT_COUNT);
    }

    public String getArticleTitle() {
        return findArticleHeader().findElement(TITLE_ARTICLE).getText();
    }

    /*
     * Return comment count of article, (article_count)
     *
     * @return - string with comment count
     */
    public String getArticleCommentCount() {
        String comment = "";
        try {
            comment = findArticleHeader().findElement(COMMENT_COUNT).getText();
        }
        catch (Exception e) {
            // Do nothing
        }
        return comment;
    }

    /*
     * Return comment count of article
     *
     * @return - comment count
     */
    public int getCommentCount() {
        int count = 0;
        try {
            count = commonFunctions.getCountFromString(getArticleCommentCount());
        }
        catch (Exception e) {
            // Do nothing
        }
        return count;
    }

    public void clickArticleCommentCount() {
        findArticleCommentCount().click();
    }


    /**
     * This method click on comment count and open comment page
     *
     * @return category page
     */
    public CommentsPage openComments() {
        this.clickArticleCommentCount();
        LOGGER.info("User clicked on the comment count ");
        return new CommentsPage(commonFunctions);
    }

}
