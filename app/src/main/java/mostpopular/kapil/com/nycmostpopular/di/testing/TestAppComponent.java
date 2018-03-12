package mostpopular.kapil.com.nycmostpopular.di.testing;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import mostpopular.kapil.com.nycmostpopular.di.MainActivityModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        TestAppModule.class,
        MainActivityModule.class
})
 interface TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        TestAppComponent build();
    }
    void inject(TestNycApp nycApp);
}
