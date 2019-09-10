package com.serghienco.ibm.newsservice.domain;

import java.util.List;
import java.util.stream.Collectors;

public class NewsProjection {

    private final News news;

    public NewsProjection(News news) {
        this.news = news;
    }

    public String getSource() {
        return news.getSource();
    }

    public String getAuthor() {
        return news.getAuthor();
    }

    public String getTitle() {
        return news.getTitle();
    }

    public String getDescription() {
        return news.getDescription();
    }

    public String getUrl() {
        return news.getUrl();
    }

    public String getUrlToImage() {
        return news.getUrlToImage();
    }

    public String getContent() {
        return news.getContent();
    }

    public List<Employee> getEmployees() {
        return news.getRecommendedNews().stream().map(rn -> rn.getEmployee()).collect(Collectors.toList());
    }
}

