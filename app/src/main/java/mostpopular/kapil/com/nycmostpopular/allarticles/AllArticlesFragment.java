package mostpopular.kapil.com.nycmostpopular.allarticles;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mostpopular.kapil.com.nycmostpopular.BaseFragment;
import mostpopular.kapil.com.nycmostpopular.NavigationController;
import mostpopular.kapil.com.nycmostpopular.R;
import mostpopular.kapil.com.nycmostpopular.ResourceLiveDataObserver;
import mostpopular.kapil.com.nycmostpopular.databinding.FragmentAllArticlesBinding;
import mostpopular.kapil.com.nycmostpopular.di.Injectable;
import mostpopular.kapil.com.nycmostpopular.models.Article;
import mostpopular.kapil.com.nycmostpopular.models.Resource;


public class AllArticlesFragment extends BaseFragment implements Injectable, LifecycleRegistryOwner,
        AllArticlesAdapter.ArticleItemInteractionListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    AllArticlesRepository allArticlesRepository;
    @Inject
    NavigationController navigationController;

    private FragmentAllArticlesBinding allArticlesFragmentBinding;

    private AllArticlesViewModel allArticlesViewModel;

    private AllArticlesAdapter allArticlesAdapter;

    public AllArticlesFragment() {
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

        allArticlesFragmentBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_all_articles, container, false);
        return allArticlesFragmentBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allArticlesViewModel = ViewModelProviders.of(this, viewModelFactory).get(AllArticlesViewModel.class);
        allArticlesViewModel.loadArticles();
        setAllArticlesRecyclerView();
        setAllNotesObserverCallback();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void setAllNotesObserverCallback() {

        allArticlesViewModel.getArticleListLiveData().observe(this, new ResourceLiveDataObserver<List<Article>>() {
            @Override
            public void onChanged(@Nullable Object o) {
                super.onChanged(o);
            }

            @Override
            public void onSuccessfulFetch(Resource<List<Article>> resource) {
                dismissProgressDialog();
                allArticlesAdapter.setArticleList(resource.data);
            }

            @Override
            public void onResourceLoading(Resource<List<Article>> resource) {
                showProgressDialog(getString(R.string.loading));
            }

            @Override
            public void onErrorFetching(Resource<List<Article>> resource) {
                dismissProgressDialog();

            }


        });

    }

    private void setAllArticlesRecyclerView() {
        allArticlesAdapter = new AllArticlesAdapter(getActivity(), this, new ArrayList<Article>());
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        allArticlesFragmentBinding.allArticlesRecyclerView.setLayoutManager(verticalLayoutManager);
        allArticlesFragmentBinding.allArticlesRecyclerView.setAdapter(allArticlesAdapter);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClicked(Article article) {
        navigationController.navigateToArticleDetail(article.getTitle(), article.getByline(),
                article.getArticleAbstract());
    }
}
