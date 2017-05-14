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
 * Created by Inga on 14/05/2017.
 */
public class CommentsListPage {
    private final CommonFunctions commonFunctions;
    private static final long WAIT_MILL = 30;
    private static final By COMMENTS_LIST = By.id("comments-list");
    private static final By COMMENT = By.className("comment-content");
    private static final By COMMENT_REPLIES = By.className("comments-list-replies");
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    public CommentsListPage(CommonFunctions commonFunctions) {
        this.commonFunctions = commonFunctions;
        commonFunctions.waitDisplayElement(COMMENTS_LIST, WAIT_MILL);
        //articleTitle = commonFunctions.getElement(HEADER_ARTICLE);
        LOGGER.info("Comment list page (by button) loaded");
    }

    /**
     * Method collects main comments items
     *
     * @return - List of comments items wrappers
     */
    private List<CommentsWrapper> getCommentsList() {
        List<WebElement> commentsList = commonFunctions.findElements(COMMENT);
        List<CommentsWrapper> result = new ArrayList<CommentsWrapper>();
        Iterables.addAll(result, Iterables.transform(commentsList, new Function<WebElement, CommentsWrapper>() {
            public CommentsWrapper apply(WebElement webElement) {
                return new CommentsWrapper(commonFunctions, webElement);
            }
        }));
        return result;
    }

    /*
      * Return  comments count
      *
      * @return comments count
     */
    public int getCommentsCount() {
        List<CommentsWrapper> comments = getCommentsList();
        return comments.size();

    }

    /**
     * Method collects main comments items
     *
     * @return - List of comments items wrappers
     */
    private List<CommentsRepliesWrapper> getCommentsRepliesList() {
        List<WebElement> commentsRepliesList = commonFunctions.findElements(COMMENT_REPLIES);
        List<CommentsRepliesWrapper> result = new ArrayList<CommentsRepliesWrapper>();
        Iterables.addAll(result, Iterables.transform(commentsRepliesList, new Function<WebElement, CommentsRepliesWrapper>() {
            public CommentsRepliesWrapper apply(WebElement webElement) {
                return new CommentsRepliesWrapper(commonFunctions, webElement);
            }
        }));
        return result;
    }

    /*
      * Return  comments count
      *
      * @return comments count
     */
    public int getCommentsRepliesCount() {
        List<CommentsRepliesWrapper> commentsReplies = getCommentsRepliesList();
        return commentsReplies.size();

    }

}
