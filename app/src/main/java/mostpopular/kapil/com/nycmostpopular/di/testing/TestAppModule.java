package mostpopular.kapil.com.nycmostpopular.di.testing;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mostpopular.kapil.com.nycmostpopular.di.ViewModelModule;

import static mostpopular.kapil.com.nycmostpopular.constants.SchedulerConstants.ANDROID_SCHEDULER;
import static mostpopular.kapil.com.nycmostpopular.constants.SchedulerConstants.PROCESSING_SCHEDULER;

@Module(includes = {ViewModelModule.class, TestAllArticlesFragmentModule.class} )
public class TestAppModule {

    @Singleton
    @Named(ANDROID_SCHEDULER)
    @Provides
    public Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Named(PROCESSING_SCHEDULER)
    @Provides
    public Scheduler providesProcessingScheduler() {
        return Schedulers.io();
    }

}
