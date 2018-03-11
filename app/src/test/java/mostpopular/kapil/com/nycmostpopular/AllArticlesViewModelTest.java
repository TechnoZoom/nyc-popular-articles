package mostpopular.kapil.com.nycmostpopular;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.schedulers.TestScheduler;
import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesRepository;
import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesViewModel;
import mostpopular.kapil.com.nycmostpopular.constants.ResourceStatus;
import mostpopular.kapil.com.nycmostpopular.data.fakes.FakeArticleSource;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class AllArticlesViewModelTest {

    private TestScheduler testScheduler;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        FakeArticleSource.getInstance().createDefaultAllArticlesResponse();
    }

    @Test
    public void onServerError_IfLiveData_HasError() throws Exception {
        String serverErrorMessage = "Some Error Occurred";
        FakeArticleSource.getInstance().createAllArticlesResponseWithServerErrorFlowable(serverErrorMessage);
        AllArticlesViewModel allArticlesViewModel = createAllArticlesViewModel();
        loadAllArticles(allArticlesViewModel);
        assertNull(allArticlesViewModel.getArticleListLiveData().getValue().data);
        assertEquals(serverErrorMessage, LiveDataTestUtil.getValue(allArticlesViewModel.getArticleListLiveData()).message);
        assertEquals(ResourceStatus.ERROR, LiveDataTestUtil.getValue(allArticlesViewModel.getArticleListLiveData()).status);
    }

    @Test
    public void onSuccessfulResponse_IfLiveData_HasCorrectData() throws Exception {
        String[] sampleTitles = new String[] {"Title 1", "Title 2", "Title 3"};
        FakeArticleSource.getInstance().createAllArticlesFlowableBasedOnTitles(sampleTitles);
        AllArticlesViewModel allArticlesViewModel = createAllArticlesViewModel();
        loadAllArticles(allArticlesViewModel);
        assertNotNull(allArticlesViewModel.getArticleListLiveData().getValue().data);
        assertNull(LiveDataTestUtil.getValue(allArticlesViewModel.getArticleListLiveData()).message);
        assertEquals(ResourceStatus.SUCCESS, LiveDataTestUtil.getValue(allArticlesViewModel.getArticleListLiveData()).status);
    }
    @Test
    public void onException_IfLiveData_HasError() throws Exception {
        String exceptionMessage = "Some Error Occurred";
        FakeArticleSource.getInstance().createExceptionErrorObservable(exceptionMessage);
        AllArticlesViewModel allArticlesViewModel = createAllArticlesViewModel();
        loadAllArticles(allArticlesViewModel);
        assertNull(allArticlesViewModel.getArticleListLiveData().getValue().data);
        assertEquals(exceptionMessage, LiveDataTestUtil.getValue(allArticlesViewModel.getArticleListLiveData()).message);
        assertEquals(ResourceStatus.ERROR, LiveDataTestUtil.getValue(allArticlesViewModel.getArticleListLiveData()).status);
    }


    private AllArticlesViewModel createAllArticlesViewModel() {
        testScheduler = new TestScheduler();
        return new AllArticlesViewModel(new AllArticlesRepository(FakeArticleSource.getInstance()),
                testScheduler,testScheduler);
    }

    private void loadAllArticles(AllArticlesViewModel allArticlesViewModel) {
        allArticlesViewModel.loadArticles();
        testScheduler.triggerActions();
    }
}