package mostpopular.kapil.com.nycmostpopular.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mostpopular.kapil.com.nycmostpopular.MainActivity;

/**
 * Created by kapilbakshi on 10/03/18.
 */

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentsBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}