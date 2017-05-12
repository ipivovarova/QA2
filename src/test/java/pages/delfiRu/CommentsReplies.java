package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 12/05/2017.
 */
public class CommentsReplies {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;

    /*
    * constructor
    */
    public CommentsReplies(CommonFunctions commonFunctions) {
        rootElement = null;
        this.commonFunctions = commonFunctions;
    }

    public CommentsReplies(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

}
