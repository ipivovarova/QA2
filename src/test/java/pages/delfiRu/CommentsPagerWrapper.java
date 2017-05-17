package pages.delfiRu;

import core.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Inga on 16/05/2017.
 */
public class CommentsPagerWrapper {
    private final CommonFunctions commonFunctions;
    private final WebElement rootElement;
    private static final By PAGE_SELECTED = By.className("comments-pager-page comments-pager-page-selected");
    private static final By NEXT_PAGE = By.xpath("./a[@class='comments-pager-page']");
    private static final By LAST_PAGE = By.className("comments-pager-arrow-last");
    private static final By FIRST_PAGE = By.className("comments-pager-arrow-first");
    private static final By COMMENTS_PAGER = By.className("comments-pager comments-pager-top");

    /*
    * constructor
    */
    public CommentsPagerWrapper(CommonFunctions commonFunctions)  {
        this.commonFunctions = commonFunctions;
        rootElement = commonFunctions.getElement(COMMENTS_PAGER);
    }

    public String getPageSelected() {
        return pageSelectedElement().getText();
    }

    private WebElement pageSelectedElement() {
        return rootElement.findElement(PAGE_SELECTED);
    }

    public CommentsListPage gotoNextPage() {
        pageSelectedElement().findElement(NEXT_PAGE).click();
        return new CommentsListPage(commonFunctions);
    }

    public CommentsListPage gotoLastPage() {
        rootElement.findElement(LAST_PAGE).click();
        return new CommentsListPage(commonFunctions);
    }

    public CommentsListPage gotoFIRSTPage() {
        rootElement.findElement(FIRST_PAGE).click();
        return new CommentsListPage(commonFunctions);
    }




}
