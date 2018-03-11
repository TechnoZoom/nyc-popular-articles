package mostpopular.kapil.com.nycmostpopular.allarticles;

import javax.inject.Inject;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.api.MostPopularApis;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;

public class AllArticlesAsyncResource implements AllArticlesDataSource {

    private MostPopularApis mostPopularApis;

    @Inject
    public AllArticlesAsyncResource(MostPopularApis mostPopularApis) {
        this.mostPopularApis = mostPopularApis;
    }

    @Override
    public Flowable<AllArticlesResponse> getArticles() {
        return mostPopularApis.getArticles();
    }
}
