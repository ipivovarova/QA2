package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 12/05/2017.
 */
public class CommentsWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;
    private static final By COMMENT_CONTENT = By.className("comment-content-inner");
    private static final By COMMENTS_PAGER = By.className("comments-pager comments-pager-top");

    /*
    * constructor
    */

    public CommentsWrapper(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

    public String getCommentText() {
        return rootElement.findElement(COMMENT_CONTENT).getText();
    }

}
