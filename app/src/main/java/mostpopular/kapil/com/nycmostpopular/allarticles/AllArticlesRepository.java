package mostpopular.kapil.com.nycmostpopular.allarticles;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;

/**
 * Created by kapilbakshi on 08/03/18.
 */

public class AllArticlesRepository implements AllArticlesDataSource {

    private AllArticlesDataSource allArticlesDataSource;

    public AllArticlesRepository(AllArticlesDataSource allArticlesDataSource) {
        this.allArticlesDataSource = allArticlesDataSource;
    }

    @Override
    public Flowable<AllArticlesResponse> getArticles() {
        return allArticlesDataSource.getArticles();
    }
}
