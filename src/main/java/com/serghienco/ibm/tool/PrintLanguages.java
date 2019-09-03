package com.serghienco.ibm.tool;

import com.serghienco.ibm.newsservice.service.NewsSourceService;

import java.util.Set;
import java.util.stream.Collectors;

public class PrintLanguages {

    public static  void main(String[] args) {
        NewsSourceService service = new NewsSourceService();
        Set<String> languages = service.getNewsSources().stream().map(s -> s.getLanguage()).collect(Collectors.toSet());
        System.out.println(languages);
    }
}
