package com.memorisehelper.savelibrary;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SearchWord {
    public static void main(String[] args) throws IOException {
        askGoogleTransator();
    }

   private List<String> translations; 

   private String word;

   public SearchWord(String word) {
        this.word = word;
   }

   private static void askGoogleTransator() throws IOException {
        String url = "https://translate.google.com/?hl=ru&sl=en&tl=ru&text=cat&op=translate";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("body > c-wiz[jsrenderer=HgVFRb] > div > div[class=ToWKne] " + 
        "> c-wiz > div[jsname=gnoFo] > c-wiz > div > div[class=AxqVh] > div[class=OPPzxe] > c-wiz[jsrenderer=FhfY2b]");

        System.out.println(elements.toString());

  }
}
