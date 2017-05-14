package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 12/05/2017.
 */
public class CommentsRepliesWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;

    /*
    * constructor
    */
    public CommentsRepliesWrapper(CommonFunctions commonFunctions) {
        rootElement = null;
        this.commonFunctions = commonFunctions;
    }

    public CommentsRepliesWrapper(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

}
