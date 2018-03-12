package mostpopular.kapil.com.nycmostpopular.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mostpopular.kapil.com.nycmostpopular.ui.MainActivity;


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentsBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}