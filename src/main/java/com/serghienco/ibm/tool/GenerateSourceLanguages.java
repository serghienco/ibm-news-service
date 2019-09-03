package com.serghienco.ibm.tool;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.serghienco.ibm.newsservice.domain.NewsSource;

import java.io.File;
import java.io.IOException;

public class GenerateSourceLanguages {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer(new DefaultPrettyPrinter());

    public static void main(String[] args) throws IOException {
        OBJECT_MAPPER.readTree(new File("./data/newsapi/sources.json")).get("sources").forEach(n -> {
            System.out.println("," + new NewsSource(
                    n.get("id").asText(),
                    n.get("name").asText(),
                   // n.get("description").asText(),
                    n.get("url").asText(),
                    n.get("category").asText(),
                    n.get("language").asText(),
                    n.get("country").asText()
            ));
        });
    }
}
