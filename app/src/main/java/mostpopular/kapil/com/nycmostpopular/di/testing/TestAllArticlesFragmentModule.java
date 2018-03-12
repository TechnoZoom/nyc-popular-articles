package mostpopular.kapil.com.nycmostpopular.di.testing;


import dagger.Module;
import dagger.Provides;
import mostpopular.kapil.com.nycmostpopular.data.fakes.FakeArticleSource;
import mostpopular.kapil.com.nycmostpopular.ui.allarticles.AllArticlesRepository;

@Module
public class TestAllArticlesFragmentModule {
    @Provides
    public AllArticlesRepository providesArticlesRepository() {
        return new AllArticlesRepository(FakeArticleSource.getInstance());
    }
}
