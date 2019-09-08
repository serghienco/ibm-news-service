package com.serghienco.ibm.tool;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GenerateNewsJson {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer(new DefaultPrettyPrinter());

    @AllArgsConstructor
    @Data
    private static class News {
        private String source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
    }

    public static void main(String[] args) throws IOException {
        List<News> newsList = readNews("./data/newsapi");
        setPublishedDate(newsList);
        OBJECT_WRITER.writeValue(new File("./data/newsapi/news.json"), newsList);
    }

    public static void setPublishedDate(List<News> newsList) {
        int size = newsList.size();
        for (int i = 0; i < size; i++) {
            int swapI = ((int) Math.random() * size);
            News temp = newsList.get(i);
            newsList.set(i, newsList.get(swapI));
            newsList.set(swapI, temp);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.now();
        String date = formatter.format(localDate);

        int count = 0;
        for (News news : newsList) {
            count++;
            news.setPublishedAt(date);
            if (count % 100 == 0) {
                localDate = localDate.minusDays(1);
                date = formatter.format(localDate);
            }
        }
    }

    public static List<News> readNews(String newsPath) throws IOException {
        List<News> nodes = new ArrayList<>();
        for (File directory : new File(newsPath).listFiles(x -> x.isDirectory())) {
            for (File jsonFile : directory.listFiles(x -> x.getName().endsWith(".json"))) {

                if (OBJECT_MAPPER.readTree(jsonFile).get("status").asText().equals("error")) {
                    jsonFile.delete();
                    continue;
                }

                OBJECT_MAPPER.readTree(jsonFile).get("articles").forEach(article -> {
                    nodes.add(new News(
                            article.get("source").get("id").asText(),
                            article.get("author").asText(),
                            article.get("title").asText(),
                            article.get("description").asText(),
                            article.get("url").asText(),
                            article.get("urlToImage").asText(),
                            "",
                            article.get("content").asText()
                    ));
                });
            }
        }
        return nodes;
    }
}
