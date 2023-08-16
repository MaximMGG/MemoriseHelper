package com.memorisehelper.libraries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SearchWord {

    private static final String URL_TRANSLATOR = "https://context.reverso.net/перевод/английский-русский/";

    public List<String> getTranslations(String word) throws IOException {
        List<String> list = new ArrayList<>();
        Elements elements = createConnection(word)
                .select("body > div[id=wrapper] > section > div[class=left-content] > section[id=top-results]" +
                        " > div[id=translations-content] > a > span[class=display-term]");
        int index = elements.size() >= 5 ? 5 : elements.size();
        for (int i = 0; i < index; i++) {
            list.add(elements.get(i).text());
        }
        return list;
    }

    private Document createConnection(String word) throws IOException {
        String url = URL_TRANSLATOR + word;
        return Jsoup.connect(url).get();

    }
}
