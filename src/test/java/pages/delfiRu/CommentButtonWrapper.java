package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 29/04/2017.
 */
public class CommentButtonWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;

    /*
    * constructor
    */
    public CommentButtonWrapper(CommonFunctions commonFunctions) {
        rootElement = null;
        this.commonFunctions = commonFunctions;
    }

    public CommentButtonWrapper(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

    public String getButtonTitle() {
        return rootElement.getText();
    }


    /*
    * Return comment type from comment button
    *
    * @return commentType  from title comment button
     */
    public String getCommentType() {
        String commentType = "";
        String buttonTitle = this.getButtonTitle();
        int posBkt = getPositionComment(buttonTitle);
        if (posBkt > 0) {
            commentType = buttonTitle.substring(0, posBkt - 1).trim();
        }
        return commentType;
    }

    /*
    * Return comment count from comment button
    *
    * @return commentCount  from title comment button
     */
    public String getButtonComment() {
        String commentCount = "";
        String buttonTitle = this.getButtonTitle();
        int posBkt = getPositionComment(buttonTitle);
        if (posBkt >= 0) {
            commentCount = buttonTitle.substring(posBkt).trim();
        }
        return commentCount;
    }

    public void clickButton() {
        rootElement.click();
    }

    /*
    * Return position symbol "(" (start comment count)
    *
    * @return position "("
     */
    private int getPositionComment(String buttonTitle) {
        int posBkt = -1;
        posBkt = buttonTitle.indexOf("(");
        return posBkt;
    }
}
