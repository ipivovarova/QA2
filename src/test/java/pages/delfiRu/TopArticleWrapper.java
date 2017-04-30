package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Wrapper for top article item from home page
 */
public class TopArticleWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;
    private static final By ARTICLE_NAME = By.xpath(".//a[@class='top2012-title']");
    private static final By COMMENT_COUNT = By.xpath(".//a[@class='comment-count']");

    /*
     * constructor
     */
    public TopArticleWrapper(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

    public WebElement findArticleName() {
        return rootElement.findElement(ARTICLE_NAME);
    }

    public WebElement findArticleCommentCount() {
        return rootElement.findElement(COMMENT_COUNT);
    }

    public String getArticleName() {
        return findArticleName().getText();
    }

    public String getArticleCommentCount() {
        return findArticleCommentCount().getText();
    }

    public void clickArticleName() {
        findArticleName().click();
    }

    public void clickArticleCommentCount() {
        findArticleCommentCount().click();
    }

}
