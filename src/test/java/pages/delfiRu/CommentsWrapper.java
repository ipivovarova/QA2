package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 12/05/2017.
 */
public class CommentsWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;

    /*
    * constructor
    */
    public CommentsWrapper(CommonFunctions commonFunctions) {
        rootElement = null;
        this.commonFunctions = commonFunctions;
    }

    public CommentsWrapper(CommonFunctions commonFunctions, WebElement element) {
        rootElement = element;
        this.commonFunctions = commonFunctions;
    }

}
