package mostpopular.kapil.com.nycmostpopular.api;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.config.ServerEndPoints;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;
import retrofit2.http.GET;

/**
 * Created by kapilbakshi on 08/03/18.
 */

public interface MostPopularApis {
    @GET(ServerEndPoints.MOST_POPULAR_APIS_ARTICLES_LIST)
    Flowable<AllArticlesResponse> getArticles();
}
