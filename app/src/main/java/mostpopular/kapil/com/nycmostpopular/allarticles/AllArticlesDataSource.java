package mostpopular.kapil.com.nycmostpopular.allarticles;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;

/**
 * Created by kapilbakshi on 08/03/18.
 */

public interface AllArticlesDataSource {

    Flowable<AllArticlesResponse> getArticles();

}
