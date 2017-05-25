package pages.delfiRu;

import com.google.common.base.Function;
import com.google.common.base.Optional;
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

    private CommonFunctions commonFunctions;

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
    public TopArticleWrapper getArticleByName(final String name) {
        Optional<TopArticleWrapper> wrapper = Iterables.tryFind(getAllTopArticles(),
                (TopArticleWrapper topArticleWrapper) -> {
                    return topArticleWrapper.getArticleName().equalsIgnoreCase(name);
                });
        return wrapper.get();
    }


    /**
     * Method returns article wrapper by name
     *
     * @param name of article item
     *
     * @return - selected article item wrapper
     */
//    public TopArticleWrapper getArticleByName(final String name) throws NullPointerException {
//        List<TopArticleWrapper> topArticleWrappers = getAllTopArticles();
//        TopArticleWrapper article = null;
//        for (TopArticleWrapper topArticleWrapper: topArticleWrappers) {
//            if (topArticleWrapper.getArticleName().contains(name)) {
//                article = topArticleWrapper;
//                break;
//            }
//        }
//        return article;
//    }


    /**
     * This method selects article by name and return new page with selected article
     *
     * @param name - article name
     * @return article page
     */
    public ArticlePage openArticleByName(String name) {
        TopArticleWrapper article = getArticleByName(name);
        article.clickArticleName();
        LOGGER.info("User clicked on the article:" + name);
        return new ArticlePage(commonFunctions);
    }

    /**
     * This method selects article by order and return new page with selected article
     *
     * @param order - article order
     * @return article page
     */
    public ArticlePage openArticleByOrder(final int order) {
        TopArticleWrapper article = getArticleByOrder(order);
        article.clickArticleName();
        LOGGER.info("User clicked on the " + order + " article");
        return new ArticlePage(commonFunctions);
    }

    /**
     * This method selects article by name and return new page with selected article
     *
     * @param article - TopArticleWrapper article
     * @return article page
     */
    public ArticlePage openArticle(TopArticleWrapper article) {
        article.clickArticleName();
        LOGGER.info("User clicked on the name of the article: "  + article.getArticleName());
        return new ArticlePage(commonFunctions);
    }

    /**
     * This method selects article by name and return new page with selected article
     *
     * @param article - TopArticleWrapper article
     * @return article page
     */
    public CommentsPage openComments(TopArticleWrapper article) {
        article.clickArticleCommentCount();
        LOGGER.info("User clicked on the comment count of the article:" + article.getArticleName());
        return new CommentsPage(commonFunctions);
    }

}
