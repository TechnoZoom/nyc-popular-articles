package mostpopular.kapil.com.nycmostpopular.ui.allarticles;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;


public interface AllArticlesDataSource {

    Flowable<AllArticlesResponse> getArticles();

}
