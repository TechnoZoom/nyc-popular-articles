package mostpopular.kapil.com.nycmostpopular.ui.allarticles;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mostpopular.kapil.com.nycmostpopular.ui.commons.CircleTransform;
import mostpopular.kapil.com.nycmostpopular.R;
import mostpopular.kapil.com.nycmostpopular.constants.ArticleConstants;
import mostpopular.kapil.com.nycmostpopular.models.Article;

public class AllArticlesAdapter extends RecyclerView.Adapter<AllArticlesAdapter.AllArticlesViewHolder> {

    private List<Article> articleList;
    private Context context;
    private ArticleItemInteractionListener articleItemInteractionListener;

    public AllArticlesAdapter(Context context, ArticleItemInteractionListener articleItemInteractionListener,
                              List<Article> articleList) {
        this.articleList = articleList;
        this.context = context;
        this.articleItemInteractionListener = articleItemInteractionListener;
    }

    @Override
    public AllArticlesAdapter.AllArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new AllArticlesViewHolder(view);
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(AllArticlesAdapter.AllArticlesViewHolder holder, int position) {
        final Article article = articleList.get(position);
        holder.getArticleTitleTextView().setText(article.getTitle());
        holder.getArticleByLineTextView().setText(article.getByline());
        holder.getArticleDateTextView().setText(article.getPublishDate());
        if(getImageUrlToBeDisplayed(article.getMediaList()) == null) {
            Picasso.with(context).load(R.drawable.default_article).error(R.drawable.default_article).
                    transform(new CircleTransform()).into(holder.getArticleByImageView());
        }
        else {
            Picasso.with(context).load(getImageUrlToBeDisplayed(article.getMediaList())).error(R.drawable.default_article).
                    transform(new CircleTransform()).into(holder.getArticleByImageView());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleItemInteractionListener.onItemClicked(article);
            }
        });

    }

    private String getImageUrlToBeDisplayed(List<Article.Media> mediaList) {
        if(mediaList == null) {
            return null;
        }
        for(Article.Media media :mediaList) {
            for(Article.Media.MediaMetaData mediaMetaData: media.getMediaMetaDataList()) {
                if(mediaMetaData.getFormat().equals(ArticleConstants.Format.STANDARD_THUMBNAIL)) {
                    return mediaMetaData.getUrl();
                }
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class AllArticlesViewHolder extends RecyclerView.ViewHolder {

        private TextView articleTitleTextView;
        private TextView articleByLineTextView;
        private ImageView articleByImageView;
        private TextView articleDateTextView;

        AllArticlesViewHolder(View itemView) {
            super(itemView);
            articleTitleTextView = (TextView) itemView.findViewById(R.id.article_item_title_text_view);
            articleByLineTextView = (TextView) itemView.findViewById(R.id.byline_text_view);
            articleDateTextView = (TextView) itemView.findViewById(R.id.article_item_date_text_view);
            articleByImageView = (ImageView) itemView.findViewById(R.id.article_image_view);
        }

        public TextView getArticleTitleTextView() {
            return articleTitleTextView;
        }

        public TextView getArticleByLineTextView() {
            return articleByLineTextView;
        }

        public ImageView getArticleByImageView() {
            return articleByImageView;
        }

        public TextView getArticleDateTextView() {
            return articleDateTextView;
        }
    }

    public interface ArticleItemInteractionListener{
        void onItemClicked(Article article);
    }
}

