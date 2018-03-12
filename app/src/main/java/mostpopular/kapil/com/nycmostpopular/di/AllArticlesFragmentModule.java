package mostpopular.kapil.com.nycmostpopular.di;

import dagger.Module;
import dagger.Provides;
import mostpopular.kapil.com.nycmostpopular.ui.allarticles.AllArticlesAsyncResource;
import mostpopular.kapil.com.nycmostpopular.ui.allarticles.AllArticlesRepository;
import mostpopular.kapil.com.nycmostpopular.api.MostPopularApis;


@Module
public class AllArticlesFragmentModule {

    @Provides
    public AllArticlesRepository providesArticlesRepository(MostPopularApis mostPopularApis) {
        return new AllArticlesRepository(new AllArticlesAsyncResource(mostPopularApis));
    }

}
