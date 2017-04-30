package pages.delfiRu;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import core.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Comments page.
 */
public class CommentsPage {
    private final CommonFunctions commonFunctions;
    private static final long WAIT_MILL = 30;
    private static final By COMMENTS_LIST = By.id("comments-listing");
    private static final By COMMENTS = By.xpath("//*[contains(@class, 'comment-thread-switcher-list-a ')]");
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    /*
    * constructor
    */
    public CommentsPage(CommonFunctions commonFunctions) {
        this.commonFunctions = commonFunctions;
        commonFunctions.waitDisplayElement(COMMENTS_LIST, WAIT_MILL);
        LOGGER.info("Article page loaded");
    }

    /**
     * Method collects all comment buttons items
     *
     * @return - List of buttons items wrappers
     */
    private List<CommentPageWrapper> getAllTopArticles() {
        List<WebElement> commentButtons = commonFunctions.findElements(COMMENTS);
        List<CommentPageWrapper> result = new ArrayList<CommentPageWrapper>();
        Iterables.addAll(result, Iterables.transform(commentButtons, new Function<WebElement, CommentPageWrapper>() {
            public CommentPageWrapper apply(WebElement webElement) {
                return new CommentPageWrapper(commonFunctions, webElement);
            }
        }));
        return result;
    }

    /*
      * Return total comment from buttons (registered and anonymous)
      *
      * @return total comment
     */
    public int getTotalComment() {
        int totalCount = 0;
        String buttonTitle = "";
        String commentType = "";
        int posCount = -1;
        String commentCountTitle = "";

        List<CommentPageWrapper> commentButtons = getAllTopArticles();
        LOGGER.info("Size: " + commentButtons.size());

        for (CommentPageWrapper commentButton : commentButtons) {
            buttonTitle = commentButton.getButtonTitle();
            commentType = commentButton.getCommentType();
            commentCountTitle = commentButton.getButtonComment();
            if (!buttonTitle.isEmpty()) {
                LOGGER.info("Comment type: " + commentType + ". Comment count: " + commentCountTitle);
                totalCount = totalCount + commonFunctions.getCountFromString(commentCountTitle);
            }
        }
        return totalCount;
    }

}
