package mostpopular.kapil.com.nycmostpopular.ui.commons;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import mostpopular.kapil.com.nycmostpopular.ui.ErrorFragment;
import mostpopular.kapil.com.nycmostpopular.ui.MainActivity;
import mostpopular.kapil.com.nycmostpopular.R;
import mostpopular.kapil.com.nycmostpopular.ui.allarticles.AllArticlesFragment;
import mostpopular.kapil.com.nycmostpopular.ui.articledetails.ArticleDetailsFragment;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToAllArticles() {
        AllArticlesFragment searchFragment = new AllArticlesFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, searchFragment)
                .commitAllowingStateLoss();
    }

    public void navigateToArticleDetail(String title, String byLine, String articleAbstract) {
        String tag = ArticleDetailsFragment.class.getSimpleName() + "/" + title;
        ArticleDetailsFragment userFragment = ArticleDetailsFragment.create(title, byLine, articleAbstract);
        fragmentManager.beginTransaction()
                .replace(containerId, userFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToErrorFragment(String errorMessage) {
        String tag = ArticleDetailsFragment.class.getSimpleName();
        ErrorFragment userFragment = ErrorFragment.create(errorMessage);
        fragmentManager.beginTransaction()
                .replace(containerId, userFragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
