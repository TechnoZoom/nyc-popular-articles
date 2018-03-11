package mostpopular.kapil.com.nycmostpopular.models;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Article {

    private String title;

    @SerializedName("published_date")
    private String publishDate;

    private String byline;

    private List<Media> mediaList;

    @SerializedName("abstract")
    private String articleAbstract;

    public Article(String title, String byline, String publishDate,List<Media> mediaList) {
        this.title = title;
        this.publishDate = publishDate;
        this.byline = byline;
        this.mediaList = mediaList;
    }

    static public class Media {

        @SerializedName("media-metadata")
        private List<MediaMetaData> mediaMetaDataList;

        static public class MediaMetaData {
            private String url;

            private String format;

            public String getUrl() {
                return url;
            }

            public String getFormat() {
                return format;
            }
        }

        public List<MediaMetaData> getMediaMetaDataList() {
            return mediaMetaDataList;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getByline() {
        return byline;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

     public static class ArticleMediaDeserializer implements JsonDeserializer<Article> {

        @Override
        public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Article article = new Gson().fromJson(json, Article.class);
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("media")) {
                JsonElement elem = jsonObject.get("media");
                if (elem != null && !elem.isJsonNull()) {
                    if(elem.isJsonPrimitive()) {
                        article.setMediaList(null);
                    }
                    else {
                        String valuesString = elem.getAsJsonArray().toString();
                        if (!TextUtils.isEmpty(valuesString)){
                            List<Media> values = new Gson().fromJson(valuesString, new TypeToken<ArrayList<Media>>() {}.getType());
                            article.setMediaList(values);
                        }
                    }

                }
            }
            return article ;
        }
    }
}
