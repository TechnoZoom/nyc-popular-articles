package mostpopular.kapil.com.nycmostpopular.di;

import dagger.Module;
import dagger.Provides;
import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesAsyncResource;
import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesRepository;
import mostpopular.kapil.com.nycmostpopular.api.MostPopularApis;


@Module
public class AllArticlesFragmentModule {

    @Provides
    public AllArticlesRepository providesNotesRepository(MostPopularApis mostPopularApis) {
        return new AllArticlesRepository(new AllArticlesAsyncResource(mostPopularApis));
    }

}
