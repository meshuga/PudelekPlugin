package eu.meshuga.pudelek.model;

import org.junit.Assert;
import org.junit.Test;

public class ArticleTest {

    @Test
    public void onCorrectUrlShouldReturnId() {
        // GIVEN
        Article sut = new Article("http://www.pudelek.pl/artykul/123/jakas_nazwa/");

        // WHEN
        Integer id = sut.getId();

        // THEN
        Assert.assertEquals(new Integer(123), id);
    }

    @Test
    public void onIncorrectUrlShouldReturnNullId() {
        // GIVEN
        Article sut = new Article("zly url");

        // WHEN
        Integer id = sut.getId();

        // THEN
        Assert.assertNull(id);
    }

    @Test
    public void onCorrectUrlShouldReturnTitle() {
        // GIVEN
        Article sut = new Article("http://www.pudelek.pl/artykul/123/jakas_nazwa/");

        // WHEN
        String title = sut.getTitle();

        // THEN
        Assert.assertEquals("jakas_nazwa", title);
    }

    @Test
    public void onIncorrectUrlShouldReturnNullTitle() {
        // GIVEN
        Article sut = new Article("zly url");

        // WHEN
        String title = sut.getTitle();

        // THEN
        Assert.assertNull(title);
    }
}
