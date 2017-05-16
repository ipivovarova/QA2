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
    private static final long WAIT_MILL = 50;
    private static final By COMMENTS_LIST = By.id("comments-list");
    private static final By COMMENT = By.className("comment-content");
    private static final By COMMENT_REPLIES = By.className("comments-list-replies");
    private static final By COMMENTS_PAGER = By.className("comments-pager comments-pager-top");
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    public CommentsListPage(CommonFunctions commonFunctions) {
        this.commonFunctions = commonFunctions;
        commonFunctions.waitDisplayElement(COMMENTS_LIST, WAIT_MILL);
        LOGGER.info("Comment list page (by button) loaded");
    }

    /**
     * Method collects main comments items
     * @param classElement - class name element
     * @return - List of comments items wrappers
     */
    private List<CommentsWrapper> getCommentsList(By classElement) {
        List<WebElement> commentsList = commonFunctions.findElements(classElement);
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
        return  getCommentsListCount(COMMENT) + getCommentsListCount(COMMENT_REPLIES);
    }


    /*
      * Return comments count from list
      * @param classElement - class name element
      * @return comments count from list
     */
    private int getCommentsListCount(By classElement) {
        List<CommentsWrapper> comments = getCommentsList(classElement);
        return comments.size();
    }

    public boolean isPager() {
        return commonFunctions.getElement(COMMENTS_PAGER).isEnabled();
    }

    public CommentsListPage clickNextPage() throws Exception {
        CommentsPagerWrapper pagerWrapper =
                new CommentsPagerWrapper(commonFunctions, commonFunctions.getElement(COMMENTS_PAGER));
        CommentsListPage nextPage = pagerWrapper.gotoNextPage();
        return nextPage;
    }

}
