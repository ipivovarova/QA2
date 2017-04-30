package pages.delfiRu;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import core.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inga on 25/04/2017.
 */
public class HomePage {

    CommonFunctions commonFunctions;

    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    private static final By TOPARTICLES = By.xpath("//*[@id='column1-top']//h3[contains(@class, 'top2012-title')]");
    private static final long WAIT_MILL = 10;

    /*
     * constructor
     */
    public HomePage(CommonFunctions commonFunctions) {
        this.commonFunctions = commonFunctions;
        commonFunctions.waitDisplayElement(TOPARTICLES, WAIT_MILL);
        LOGGER.info("Home page is loaded");
    }

    /**
     * Method collects all top articles items
     *
     * @return - List of article items wrappers
     */
    private List<TopArticleWrapper> getAllTopArticles() {
        List<WebElement> topArticles = commonFunctions.findElements(TOPARTICLES);
        List<TopArticleWrapper> result = new ArrayList<TopArticleWrapper>();
        Iterables.addAll(result, Iterables.transform(topArticles, new Function<WebElement, TopArticleWrapper>() {
            public TopArticleWrapper apply(WebElement webElement) {
                return new TopArticleWrapper(commonFunctions, webElement);
            }
        }));
        return result;
    }

    /**
     * Method returns article wrapper by order number
     *
     * @param order of article item
     *
     * @return - selected article item wrapper
     */
    public TopArticleWrapper getArticleByOrder(final int order) {
        return  getAllTopArticles().get(order);
    }



    /**
     * Method returns article wrapper by name
     *
     * @param name of article item
     *
     * @return - selected article item wrapper
     */
/*    public TopArticleWrapper getArticleByName(final String name) {
        Optional<TopArticleWrapper> wrapper = Iterables.tryFind(getAllTopArticles(), new Predicate<TopArticleWrapper>() {
            public boolean apply(TopArticleWrapper topArticleWrapper) {
                return topArticleWrapper.getArticleName().contains(name);
            }
        });
        return wrapper.get();
    }
*/

    /**
     * This method selects article by name and return new page with selected article
     *
     * @param name - article name
     * @return article page
     */
/*    public ArticlePage openArticle(String name) {
        TopArticleWrapper article = getArticleByName(name);
        article.clickArticleName();
        LOGGER.info("User clicked on the " + name + " article");
        return new ArticlePage(commonFunctions);
    }
*/

    /**
     * This method selects article by order and return new page with selected article
     *
     * @param order - article order
     * @return article page
     */
    public ArticlePage openArticle(final int order) {
        TopArticleWrapper article = getArticleByOrder(order);
        article.clickArticleName();
        LOGGER.info("User clicked on the " + order + " article");
        return new ArticlePage(commonFunctions);
    }


}
