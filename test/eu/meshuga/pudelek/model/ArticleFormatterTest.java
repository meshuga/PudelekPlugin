package eu.meshuga.pudelek.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;

public class ArticleFormatterTest {
    
    @Test
    public void onClosedHtmlShouldReturnValidHtml() throws Exception {
        // GIVEN
        String html = "<html><body><article><div class=\"some-entry\">Text</div></article></body></html>";
        ArticleFormatter sut = new ArticleFormatter();
        
        // WHEN
        StringReader reader = new StringReader(html);
        reader.close();
        String result = sut.extractArticle(reader);
        
        // THEN
        Assert.assertEquals("<html><body></body></html>", result);
    }
    
    @Test
    public void onInvalidHtmlShouldReturnValidHtml() throws Exception {
        // GIVEN
        String html = "<html><body><article><div class=\"some-entry\">Text</div></article></body></html>";
        ArticleFormatter sut = new ArticleFormatter();
        
        // WHEN
        String result = sut.extractArticle(new StringReader(html));
        
        // THEN
        Assert.assertEquals("<html><body></body></html>", result);
    }
    
    @Test
    public void onValidHtmlShouldReturnValidExtractedHtml() throws Exception {
        // GIVEN
        String html = "<html><body><article><div class=\"single-entry\">Text</div></article></body></html>";
        ArticleFormatter sut = new ArticleFormatter();
        
        // WHEN
        String result = sut.extractArticle(new StringReader(html));
        
        // THEN
        Assert.assertEquals("<html><body><div class=\"single-entry\">\n" +
                " Text\n" +
                "</div></body></html>", result);
    }
}
