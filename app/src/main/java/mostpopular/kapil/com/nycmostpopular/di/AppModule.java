package mostpopular.kapil.com.nycmostpopular.di;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mostpopular.kapil.com.nycmostpopular.BuildConfig;
import mostpopular.kapil.com.nycmostpopular.api.MostPopularApis;
import mostpopular.kapil.com.nycmostpopular.config.ServerEndPoints;
import mostpopular.kapil.com.nycmostpopular.models.Article;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mostpopular.kapil.com.nycmostpopular.constants.SchedulerConstants.ANDROID_SCHEDULER;
import static mostpopular.kapil.com.nycmostpopular.constants.SchedulerConstants.PROCESSING_SCHEDULER;

@Module(includes = {ViewModelModule.class, AllArticlesFragmentModule.class} )
public class AppModule {

    @Singleton
    @Provides
    public MostPopularApis provideMostPopularApis() {

        OkHttpClient.Builder httpClient;
        httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        OkHttpClient okHttpClient = httpClient.build();

        final  Gson gson = new GsonBuilder()
                .registerTypeAdapter(Article.class, new Article.ArticleMediaDeserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl(ServerEndPoints.MOST_POPULAR_APIS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MostPopularApis.class);
    }
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
