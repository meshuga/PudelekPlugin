package eu.meshuga.pudelek.model;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.net.HttpConfigurable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleRepository {

    private static final Logger LOG = Logger.getInstance(ArticleRepository.class);
    private HttpConfigurable httpConfigurable;

    public ArticleRepository() {
        httpConfigurable = ApplicationManager.getApplication().getComponent(HttpConfigurable.class);
    }

    public List<Article> getArticles() {
        Set<Article> articlesSet = new HashSet<Article>();

        try {
            URLConnection httpURLConnection = httpConfigurable.openHttpConnection("http://pudelek.pl");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));

            String str;
            while ((str = in.readLine()) != null) {
                addArticle(articlesSet, str);
            }

            in.close();
        } catch (MalformedURLException e) {
            LOG.info(e);
        } catch (IOException e) {
            LOG.info(e);
        }
        List<Article> articles = new ArrayList<Article>(articlesSet);

        Collections.sort(articles, new ArticleComparator());

        return articles;
    }

    private void addArticle(Set<Article> articlesSet, String str) {
        String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+@/%=~_()|!:,.;]*[-A-Za-z0-9+@/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            String urlStr = m.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            if (urlStr.contains("pudelek.pl/artykul/")) {
                articlesSet.add(new Article(urlStr));
            }
        }
    }
}
