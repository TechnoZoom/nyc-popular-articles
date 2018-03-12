package mostpopular.kapil.com.nycmostpopular.ui.allarticles;

import javax.inject.Inject;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.api.MostPopularApis;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;

public class AllArticlesAsyncResource implements AllArticlesDataSource {

    static {
        System.loadLibrary("keys");
    }

    public native String getNYCApiKey();


    private MostPopularApis mostPopularApis;

    @Inject
    public AllArticlesAsyncResource(MostPopularApis mostPopularApis) {
        this.mostPopularApis = mostPopularApis;
    }

    @Override
    public Flowable<AllArticlesResponse> getArticles() {
        return mostPopularApis.getArticles(getNYCApiKey());
    }
}
