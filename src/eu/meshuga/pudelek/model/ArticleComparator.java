package eu.meshuga.pudelek.model;

import java.util.Comparator;

class ArticleComparator implements Comparator<Article> {
    @Override
    public int compare(Article o1, Article o2) {
        return o2.getId() - o1.getId();
    }
}
