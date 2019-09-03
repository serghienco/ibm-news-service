package com.serghienco.ibm.newsservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsSource {

    private String id;
    private String name;
    private String url;
    private String category;
    private String language;
    private String country;

    @Override
    public String toString() {
        String[] array = new String[]{id, name, url, category, language, country};
        asStringValues(array);
        return "new NewsSource(" + String.join(",", array) + ")";
    }

    private static void asStringValues(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = asStringValue(array[i]);
        }
    }

    private static String asStringValue(String s) {
        if (s == null) {
            return "null";
        }
        return "\"" + s.replaceAll("\"", "\\\\\"") + "\"";
    }
}
