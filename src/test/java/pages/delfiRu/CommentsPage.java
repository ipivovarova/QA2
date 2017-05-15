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
    private static final By COMMENTS_LISTING = By.id("comments-listing");
    private static final By COMMENTS_BUTTON = By.xpath("//*[contains(@class, 'comment-thread-switcher-list-a ')]");
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    /*
    * constructor
    */
    public CommentsPage(CommonFunctions commonFunctions) {
        this.commonFunctions = commonFunctions;
        commonFunctions.waitDisplayElement(COMMENTS_LISTING, WAIT_MILL);
        LOGGER.info("Article page loaded");
    }

    /**
     * Method collects all comment buttons items
     *
     * @return - List of buttons items wrappers
     */
    private List<CommentButtonWrapper> getCommentButtons() {
        List<WebElement> commentButtons = commonFunctions.findElements(COMMENTS_BUTTON);
        List<CommentButtonWrapper> result = new ArrayList<CommentButtonWrapper>();
        Iterables.addAll(result, Iterables.transform(commentButtons, new Function<WebElement, CommentButtonWrapper>() {
            public CommentButtonWrapper apply(WebElement webElement) {
                return new CommentButtonWrapper(commonFunctions, webElement);
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
        String commentCountTitle = "";

        List<CommentButtonWrapper> commentButtons = getCommentButtons();
        LOGGER.info("Size: " + commentButtons.size());

        for (CommentButtonWrapper commentButton : commentButtons) {
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

    /*
      * Return total comment from comments list after click on the buttons (registered and anonymous)
      *
      * @return total count of the real comments
     */
    public int getRealTotalComments() {
        int totalComments =0;
        List<CommentButtonWrapper> commentButtons = getCommentButtons();
        for (CommentButtonWrapper commentButton : commentButtons) {
            // we click on the button and main comments list is displied;
            CommentsListPage commentListPage = getCommentsList(commentButton);
            totalComments += commentListPage.getCommentsCount();
        }
        return totalComments;
    }

    private CommentsListPage getCommentsList(CommentButtonWrapper commentButton) {
        commentButton.clickButton();
        LOGGER.info("User clicked on the comment button");
        return new CommentsListPage(commonFunctions);
    }

}
