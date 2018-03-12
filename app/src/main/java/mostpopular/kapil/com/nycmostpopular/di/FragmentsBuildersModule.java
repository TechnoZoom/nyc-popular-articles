package mostpopular.kapil.com.nycmostpopular.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mostpopular.kapil.com.nycmostpopular.ui.ErrorFragment;
import mostpopular.kapil.com.nycmostpopular.ui.allarticles.AllArticlesFragment;
import mostpopular.kapil.com.nycmostpopular.ui.articledetails.ArticleDetailsFragment;


@Module
public abstract class FragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract AllArticlesFragment bindAllArticlesFragment();

    @ContributesAndroidInjector
    abstract ArticleDetailsFragment bindArticleDetailsFragment();

    @ContributesAndroidInjector
    abstract ErrorFragment bindErrorFragment();
}
