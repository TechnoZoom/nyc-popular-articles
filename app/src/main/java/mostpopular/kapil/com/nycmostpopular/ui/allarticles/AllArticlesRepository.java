package mostpopular.kapil.com.nycmostpopular.ui.allarticles;

import io.reactivex.Flowable;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;


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
