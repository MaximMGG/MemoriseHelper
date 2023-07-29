package com.memorisehelper.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class MemoriseStarter {

    private static final String URL_TRANSLATOR = "https://context.reverso.net/перевод/английский-русский/";
    
    public static void main(String[] args) throws IOException {
        MemoriseStarter ms = new MemoriseStarter();
        // Document document = ms.createConnection("implimentysing");
        // System.out.println(ms.correctWord(document));
        System.out.println(ms.askInGoogleCorrectWord("exist"));
        
    }

    private List<String> getTranslations(String word) throws IOException {
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

    private String askInGoogleCorrectWord(String word) throws IOException {
            String url = "https://www.google.com/search?q=" + word;
            Document doc = Jsoup.connect(url).get();
            Elements el = doc.select("body > div[class=main] > div > div[class=GyAeWb] > div[class=s6JM6d] > div[id=taw]" +
            " > div[id=oFNiHe] > p > a > b > i");
            return el.text();
    }
}
