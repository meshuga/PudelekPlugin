package eu.meshuga.pudelek.model;

import com.intellij.openapi.diagnostic.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Reader;

public class ArticleFormatter {

    private static final Logger LOG = Logger.getInstance(ArticleFormatter.class);
    
    public String extractArticle(Reader reader) {
        String pudelekPage = "";
        try {
            pudelekPage = read(reader);
        } catch (IOException e) {
            LOG.info(e);
        }
        Document doc = Jsoup.parse(pudelekPage);
        Elements links = doc.select(".single-entry");
        Element article = links.first();
        
        String articleHtml = "";
        if (article != null) {
            article.select("script, fb|like").remove();
            articleHtml = article.outerHtml();
            articleHtml = articleHtml
                    .replaceAll("<span[^>]*>", "<p>")
                    .replaceAll("</span>", "</p>")
                    .replaceAll(" width=\"\\d+\">", ">");
        }
        
        return "<html><body>" + articleHtml + "</body></html>";
    }

    public static String read(Reader reader) throws IOException {
        char[] arr = new char[8 * 1024]; // 8K at a time
        StringBuilder buf = new StringBuilder();
        int numChars;

        while ((numChars = reader.read(arr, 0, arr.length)) > 0) {
            buf.append(arr, 0, numChars);
        }

        return buf.toString();
    }
}
