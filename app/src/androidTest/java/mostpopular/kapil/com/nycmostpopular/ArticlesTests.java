package mostpopular.kapil.com.nycmostpopular;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mostpopular.kapil.com.nycmostpopular.custom.RecyclerViewItemCountAssertion;
import mostpopular.kapil.com.nycmostpopular.data.fakes.FakeArticleSource;
import mostpopular.kapil.com.nycmostpopular.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static mostpopular.kapil.com.nycmostpopular.TestUtils.withRecyclerView;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class ArticlesTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true,
            false);

    @Before
    public void setUp() {
        FakeArticleSource.getInstance().createDefaultAllArticlesResponse();
    }

    @Test
    public void onExceptionError_checkIfErrorIsDisplayedCorrectly() {
        String exceptionText = "Security Exception! You are not allowed to access";
        FakeArticleSource.getInstance().createExceptionErrorObservable(exceptionText);
        reloadOrdersActivity();
        onView(allOf(withId(R.id.error_text_view), withText(exceptionText)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onArticlesFetching_CheckArticleCount() {
        FakeArticleSource.getInstance().createCertainNumberOfArticles(5);
        reloadOrdersActivity();
        onView(withId(R.id.all_articles_recycler_view)).check(new RecyclerViewItemCountAssertion(5));
    }

    @Test
    public void onClickingAnArticle_CheckDetailsAreDisplayedCorrectly() {
        FakeArticleSource.getInstance().createCertainNumberOfArticles(5);
        reloadOrdersActivity();
        onView(withRecyclerView(R.id.all_articles_recycler_view).atPosition(3)).perform(click());

        onView(allOf(withId(R.id.article_title_text_view), withText(FakeArticleSource.ALL_ARTICLES_RESPONSE.
                getArticleList().get(3).getTitle())))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.article_byline_text_view), withText(FakeArticleSource.ALL_ARTICLES_RESPONSE.
                getArticleList().get(3).getByline())))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.article_abstract_text_view), withText(FakeArticleSource.ALL_ARTICLES_RESPONSE.
                getArticleList().get(3).getArticleAbstract())))
                .check(matches(isDisplayed()));

    }


    @Test
    public void onNoArticlesFetched_checkIfEmptyListMessageIsDisplayedCorrectly() {
        reloadOrdersActivity();
        onView(allOf(withId(R.id.error_text_view), withText(R.string.no_articles_available)))
                .check(matches(isDisplayed()));
    }

    private void reloadOrdersActivity() {
        mActivityTestRule.launchActivity(new Intent());
    }

}
