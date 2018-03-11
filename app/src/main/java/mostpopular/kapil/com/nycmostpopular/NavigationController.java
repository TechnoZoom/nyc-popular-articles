package mostpopular.kapil.com.nycmostpopular;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import mostpopular.kapil.com.nycmostpopular.allarticles.AllArticlesFragment;
import mostpopular.kapil.com.nycmostpopular.articledetails.ArticleDetailsFragment;

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
}
