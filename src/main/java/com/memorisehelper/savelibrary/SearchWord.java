package com.memorisehelper.savelibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SearchWord {


    private static final String URL_TRANSLATOR = "https://context.reverso.net/перевод/английский-русский/";

    
 
    private static void getWord() throws IOException {
        String url = "https://context.reverso.net/перевод/английский-русский/cat";
        Document doc = Jsoup.connect(url).get();
        // Elements elements = doc.select("body");
        Elements elements = doc.select("body > div[id=wrapper] > section > div[class=left-content] > section[id=top-results]");
        System.out.println(elements.toString());
    }

    public List<String> getTranslations(String word) throws IOException {
        List<String> list = new ArrayList<>();
        Elements elements = createConnection(word).select("body > div[id=wrapper] > section > div[class=left-content] > section[id=top-results]" + 
        " > div[id=translations-content] > a > span[class=display-term]");
        for (int i = 0; i < 5; i++) {
            list.add(elements.get(i).text());
        }
        return list;
    }

    private String correctWord(Document document) {
        Elements elements = document.select("body > div[id=wrapper] > section[id=body-content] > div[class=left-content]" +
        " > section[id=top-results] > section[id=search-options] > div[class=title-content]");
        return elements.toString();
    }


    private Document createConnection(String word) throws IOException {
        String url = URL_TRANSLATOR + word;
        return Jsoup.connect(url).get();
    
    }
}
