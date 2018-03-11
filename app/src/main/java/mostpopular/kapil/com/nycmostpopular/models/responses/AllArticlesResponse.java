package mostpopular.kapil.com.nycmostpopular.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mostpopular.kapil.com.nycmostpopular.models.Article;


public class AllArticlesResponse {

    @SerializedName("results")
    private List<Article> articleList;

    private String status;

    private String message;

    public AllArticlesResponse(String status, String message, List<Article> articleList) {
        this.articleList = articleList;
        this.status = status;
        this.message = message;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class StatusConstants {

        public static final String OK_STATUS = "OK";
        public static final String ERROR_STATUS = "ERROR";
    }

}
