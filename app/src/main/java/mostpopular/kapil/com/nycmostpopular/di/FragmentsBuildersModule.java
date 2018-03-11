package mostpopular.kapil.com.nycmostpopular.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesFragment;
import mostpopular.kapil.com.nycmostpopular.articledetails.ArticleDetailsFragment;

/**
 * Created by kapilbakshi on 08/03/18.
 */

@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract AllArticlesFragment bindAllArticlesFragment();

    @ContributesAndroidInjector
    abstract ArticleDetailsFragment bindArticleDetailsFragment();
}
