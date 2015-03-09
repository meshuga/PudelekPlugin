package eu.meshuga.pudelek.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Article {

    private Integer id;

    private String title;

    private String url;

    public Article(String url) {
        this.url = url;
        this.id = getId(url);
        this.title = getTitle(url);
    }

    private Integer getId(String url) {
        if (url == null) {
            return null;
        }
        String regex = "artykul/(\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (!m.find()) {
            return null;
        }
        String id = m.group(1);

        return Integer.valueOf(id);
    }

    private String getTitle(String url) {
        if (url == null) {
            return null;
        }
        String regex = "artykul/\\d+/([^/]+)/?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (!m.find()) {
            return null;
        }
        return m.group(1);
    }

    public String getUrl() {
        return url;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (url != null ? !url.equals(article.url) : article.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }
}
