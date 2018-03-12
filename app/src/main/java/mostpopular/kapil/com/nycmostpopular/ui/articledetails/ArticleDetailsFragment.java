package mostpopular.kapil.com.nycmostpopular.ui.articledetails;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mostpopular.kapil.com.nycmostpopular.ui.BaseFragment;
import mostpopular.kapil.com.nycmostpopular.R;
import mostpopular.kapil.com.nycmostpopular.databinding.FragmentArticleDetailBinding;
import mostpopular.kapil.com.nycmostpopular.di.Injectable;


public class ArticleDetailsFragment extends BaseFragment implements Injectable, LifecycleRegistryOwner {

    private static final String ARTICLE_TITTLE = "Article Title";
    private static final String ARTICLE_BYLINE = "Article Byline";
    private static final String ARTICLE_ABSTRACT = "Article Abstract";


    private FragmentArticleDetailBinding articleDetailFragmentBinding;

    public ArticleDetailsFragment() {
    }

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        articleDetailFragmentBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_article_detail, container, false);
        return articleDetailFragmentBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            if(args.containsKey(ARTICLE_TITTLE) && args.getString(ARTICLE_TITTLE) != null) {
                articleDetailFragmentBinding.articleTitleTextView.setText(args.getString(ARTICLE_TITTLE));
            }

            if(args.containsKey(ARTICLE_BYLINE) && args.getString(ARTICLE_BYLINE) != null) {
                articleDetailFragmentBinding.articleBylineTextView.setText(args.getString(ARTICLE_BYLINE));
            }

            if(args.containsKey(ARTICLE_ABSTRACT) && args.getString(ARTICLE_ABSTRACT) != null) {
                articleDetailFragmentBinding.articleAbstractTextView.setText(args.getString(ARTICLE_ABSTRACT));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static ArticleDetailsFragment create(String title, String byline, String articleAbstract) {
        ArticleDetailsFragment articleDetailsFragment = new ArticleDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARTICLE_TITTLE, title);
        args.putString(ARTICLE_BYLINE, byline);
        args.putString(ARTICLE_ABSTRACT, articleAbstract);
        articleDetailsFragment.setArguments(args);
        return articleDetailsFragment;
    }

}
