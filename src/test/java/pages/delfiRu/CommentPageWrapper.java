package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 29/04/2017.
 */
public class CommentPageWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;

    /*
    * constructor
    */
    public CommentPageWrapper(CommonFunctions commonFunctions) {
        rootElement = null;
        this.commonFunctions = commonFunctions;
    }

    public CommentPageWrapper(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

    public String getButtonTitle() {
        return rootElement.getText();
    }

    /*
    * Returm string of comment type from comment button
    *
    * @param buttonTitle - common button title
    * @return commentType  from title comment button
     */
    private String getCommentType(String buttonTitle) {
        String commentType = "";
        int posBkt = getPositionComment(buttonTitle);
        if (posBkt >= 0) {
            commentType = buttonTitle.substring(0, posBkt - 1).trim();
        }
        return commentType;
    }

    /*
    * Returm string of comment type from comment button
    *
    * @return commentType  from title comment button
     */
    public String getCommentType() {
        return getCommentType(getButtonTitle());
    }

    /*
    * Returm string of comment count from comment button
    *
    * @return commentCount  from title comment button
     */
    private String getButtonComment(String buttonTitle) {
        String commentCount = "";
        int posBkt = getPositionComment(buttonTitle);
        if (posBkt >= 0) {
            commentCount = buttonTitle.substring(posBkt).trim();
        }
        return commentCount;
    }

    /*
    * Returm string of comment count from comment button
    *
    * @return commentCount  from title comment button
     */
    public String getButtonComment() {
        return getButtonComment(getButtonTitle());
    }

    /*
    * Return position simbol "(" (start comment count)
    *
    * @return position "("
     */
    private int getPositionComment(String buttonTitle) {
        int posBkt = -1;
        posBkt = buttonTitle.indexOf("(");
        return posBkt;
    }
}
