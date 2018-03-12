package mostpopular.kapil.com.nycmostpopular.ui.allarticles;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;
import mostpopular.kapil.com.nycmostpopular.constants.SchedulerConstants;
import mostpopular.kapil.com.nycmostpopular.models.Article;
import mostpopular.kapil.com.nycmostpopular.models.Resource;
import mostpopular.kapil.com.nycmostpopular.models.responses.AllArticlesResponse;

public class AllArticlesViewModel extends ViewModel {


    private AllArticlesRepository allArticlesRepository;
    private Scheduler androidScheduler;
    private Scheduler processingScheduler;

    private MutableLiveData<Resource<List<Article>>> articleListLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public AllArticlesViewModel(AllArticlesRepository allArticlesRepository,
                                @Named(SchedulerConstants.ANDROID_SCHEDULER) Scheduler androidScheduler,
                                @Named(SchedulerConstants.PROCESSING_SCHEDULER) Scheduler processingScheduler) {
        this.allArticlesRepository = allArticlesRepository;
        this.androidScheduler = androidScheduler;
        this.processingScheduler = processingScheduler;

    }


    public void setAllArticlesRepository(AllArticlesRepository allArticlesRepository) {
        this.allArticlesRepository = allArticlesRepository;
    }

    public void loadArticles() {
        articleListLiveData.setValue(Resource.<List<Article>>loading(null));
        disposables.add(allArticlesRepository.getArticles().subscribeOn(processingScheduler)
                .observeOn(androidScheduler)
                .subscribeWith(new DisposableSubscriber<AllArticlesResponse>() {
                    @Override
                    public void onNext(AllArticlesResponse allArticlesResponse) {
                        if (allArticlesResponse.getStatus().equals(AllArticlesResponse.StatusConstants.OK_STATUS)) {
                            articleListLiveData.setValue(Resource.success(allArticlesResponse.getArticleList()));

                        } else {
                            articleListLiveData.setValue(Resource.<List<Article>>error(allArticlesResponse.getMessage(), null));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        articleListLiveData.setValue(Resource.<List<Article>>error(t.getMessage(), null));
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public MutableLiveData<Resource<List<Article>>> getArticleListLiveData() {
        return articleListLiveData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
