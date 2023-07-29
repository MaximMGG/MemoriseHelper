package com.memorisehelper.project;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class MemoriseStarter {
    public static void main(String[] args) throws IOException {
        getWord();
    }

    private static void getWord() throws IOException {
        String url = "https://context.reverso.net/перевод/английский-русский/cat";
        Document doc = Jsoup.connect(url).get();
        // Elements elements = doc.select("body");
        Elements elements = doc.select("body > div[id=wrapper] > section > div[class=left-content] > section[id=top-results]");
        System.out.println(elements.toString());

    }
}
