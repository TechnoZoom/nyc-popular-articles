package mostpopular.kapil.com.nycmostpopular.data.fakes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesDataSource;
import mostpopular.kapil.com.nycmostpopular.models.Article;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;

public class FakeArticleSource implements AllArticlesDataSource {


    private static final String DEFAULT_ARTICLE_TITLE = "TITLE";
    private static final String DEFAULT_ARTICLE_BYLINE = "BY LINE";
    private static final String DEFAULT_ARTICLE_PUBLISH_DATE = "PUBLISH DATE";

    public static Flowable<AllArticlesResponse> ALL_ARTICLES_FLOWABLE;
    private static AllArticlesResponse ALL_ARTICLES_RESPONSE;

    private static FakeArticleSource INSTANCE;

    public static FakeArticleSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeArticleSource();
        }
        return INSTANCE;
    }

    public void createDefaultAllArticlesResponse() {
        String errorMessage = null;
        String status = AllArticlesResponse.StatusConstants.OK_STATUS;
        List<Article> articleList = new ArrayList<Article>();
        ALL_ARTICLES_RESPONSE = new AllArticlesResponse(status, errorMessage, articleList);
        ALL_ARTICLES_FLOWABLE = Flowable.just(ALL_ARTICLES_RESPONSE);

    }

    public void reCreateAllArticlesResponse() {
        createDefaultAllArticlesResponse();
    }

    public void createAllArticlesResponseWithServerErrorFlowable(String errorMessage) {
        reCreateAllArticlesResponse();
        ALL_ARTICLES_RESPONSE.setMessage(errorMessage);
        ALL_ARTICLES_RESPONSE.setStatus(AllArticlesResponse.StatusConstants.ERROR_STATUS);
        ALL_ARTICLES_FLOWABLE = Flowable.just(ALL_ARTICLES_RESPONSE);
    }


    public void createExceptionErrorObservable(String exceptionMessage) {
        ALL_ARTICLES_FLOWABLE = Flowable.<AllArticlesResponse>error(new NullPointerException(exceptionMessage));
    }


    public void addArticles(Article... articles) {
        if (articles != null) {
            for (Article article : articles) {
                ALL_ARTICLES_RESPONSE.getArticleList().add(article);
            }
        }
    }

    public void createAllArticlesFlowableBasedOnTitles(String... titles) {
        List<Article> articleList = new ArrayList<>();
        for (String title : titles) {
            Article article = new Article(title, DEFAULT_ARTICLE_BYLINE, DEFAULT_ARTICLE_PUBLISH_DATE, new ArrayList<>());
            articleList.add(article);
        }
        ALL_ARTICLES_RESPONSE.setArticleList(articleList);
        ALL_ARTICLES_FLOWABLE = Flowable.just(ALL_ARTICLES_RESPONSE);
    }

    @Override
    public Flowable<AllArticlesResponse> getArticles() {
        return ALL_ARTICLES_FLOWABLE;
    }
}
