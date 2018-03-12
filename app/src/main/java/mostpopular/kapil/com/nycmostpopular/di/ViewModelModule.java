package mostpopular.kapil.com.nycmostpopular.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import mostpopular.kapil.com.nycmostpopular.NYCMostPopularModelFactory;
import mostpopular.kapil.com.nycmostpopular.ui.allarticles.AllArticlesViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllArticlesViewModel.class)
    abstract ViewModel bindAllArticlesViewModel(AllArticlesViewModel allArticlesViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(NYCMostPopularModelFactory factory);
}
