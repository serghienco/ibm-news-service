package com.serghienco.ibm.newsservice.service;

import com.serghienco.ibm.newsservice.domain.News;
import com.serghienco.ibm.newsservice.domain.NewsSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsSourceService {

    private Map<String, NewsSource> newsSourceMap = Arrays.asList(
            new NewsSource("abc-news", "ABC News", "https://abcnews.go.com", "general", "en", "us")
            , new NewsSource("abc-news-au", "ABC News (AU)", "http://www.abc.net.au/news", "general", "en", "au")
            , new NewsSource("aftenposten", "Aftenposten", "https://www.aftenposten.no", "general", "no", "no")
            , new NewsSource("al-jazeera-english", "Al Jazeera English", "http://www.aljazeera.com", "general", "en", "us")
            , new NewsSource("ansa", "ANSA.it", "http://www.ansa.it", "general", "it", "it")
            , new NewsSource("argaam", "Argaam", "http://www.argaam.com", "business", "ar", "sa")
            , new NewsSource("ars-technica", "Ars Technica", "http://arstechnica.com", "technology", "en", "us")
            , new NewsSource("ary-news", "Ary News", "https://arynews.tv/ud/", "general", "ud", "pk")
            , new NewsSource("associated-press", "Associated Press", "https://apnews.com/", "general", "en", "us")
            , new NewsSource("australian-financial-review", "Australian Financial Review", "http://www.afr.com", "business", "en", "au")
            , new NewsSource("axios", "Axios", "https://www.axios.com", "general", "en", "us")
            , new NewsSource("bbc-news", "BBC News", "http://www.bbc.co.uk/news", "general", "en", "gb")
            , new NewsSource("bbc-sport", "BBC Sport", "http://www.bbc.co.uk/sport", "sports", "en", "gb")
            , new NewsSource("bild", "Bild", "http://www.bild.de", "general", "de", "de")
            , new NewsSource("blasting-news-br", "Blasting News (BR)", "http://br.blastingnews.com", "general", "pt", "br")
            , new NewsSource("bleacher-report", "Bleacher Report", "http://www.bleacherreport.com", "sports", "en", "us")
            , new NewsSource("bloomberg", "Bloomberg", "http://www.bloomberg.com", "business", "en", "us")
            , new NewsSource("breitbart-news", "Breitbart News", "http://www.breitbart.com", "general", "en", "us")
            , new NewsSource("business-insider", "Business Insider", "http://www.businessinsider.com", "business", "en", "us")
            , new NewsSource("business-insider-uk", "Business Insider (UK)", "http://uk.businessinsider.com", "business", "en", "gb")
            , new NewsSource("buzzfeed", "Buzzfeed", "https://www.buzzfeed.com", "entertainment", "en", "us")
            , new NewsSource("cbc-news", "CBC News", "http://www.cbc.ca/news", "general", "en", "ca")
            , new NewsSource("cbs-news", "CBS News", "http://www.cbsnews.com", "general", "en", "us")
            , new NewsSource("cnbc", "CNBC", "http://www.cnbc.com", "business", "en", "us")
            , new NewsSource("cnn", "CNN", "http://us.cnn.com", "general", "en", "us")
            , new NewsSource("cnn-es", "CNN Spanish", "http://cnnespanol.cnn.com/", "general", "es", "us")
            , new NewsSource("crypto-coins-news", "Crypto Coins News", "https://www.ccn.com", "technology", "en", "us")
            , new NewsSource("daily-mail", "Daily Mail", "http://www.dailymail.co.uk/home/index.html", "entertainment", "en", "gb")
            , new NewsSource("der-tagesspiegel", "Der Tagesspiegel", "http://www.tagesspiegel.de", "general", "de", "de")
            , new NewsSource("die-zeit", "Die Zeit", "http://www.zeit.de/index", "business", "de", "de")
            , new NewsSource("el-mundo", "El Mundo", "http://www.elmundo.es", "general", "es", "es")
            , new NewsSource("engadget", "Engadget", "https://www.engadget.com", "technology", "en", "us")
            , new NewsSource("entertainment-weekly", "Entertainment Weekly", "http://www.ew.com", "entertainment", "en", "us")
            , new NewsSource("espn", "ESPN", "http://espn.go.com", "sports", "en", "us")
            , new NewsSource("espn-cric-info", "ESPN Cric Info", "http://www.espncricinfo.com/", "sports", "en", "us")
            , new NewsSource("financial-post", "Financial Post", "http://business.financialpost.com", "business", "en", "ca")
            , new NewsSource("focus", "Focus", "http://www.focus.de", "general", "de", "de")
            , new NewsSource("football-italia", "Football Italia", "http://www.football-italia.net", "sports", "en", "it")
            , new NewsSource("fortune", "Fortune", "http://fortune.com", "business", "en", "us")
            , new NewsSource("four-four-two", "FourFourTwo", "http://www.fourfourtwo.com/news", "sports", "en", "gb")
            , new NewsSource("fox-news", "Fox News", "http://www.foxnews.com", "general", "en", "us")
            , new NewsSource("fox-sports", "Fox Sports", "http://www.foxsports.com", "sports", "en", "us")
            , new NewsSource("globo", "Globo", "http://www.globo.com/", "general", "pt", "br")
            , new NewsSource("google-news", "Google News", "https://news.google.com", "general", "en", "us")
            , new NewsSource("google-news-ar", "Google News (Argentina)", "https://news.google.com", "general", "es", "ar")
            , new NewsSource("google-news-au", "Google News (Australia)", "https://news.google.com", "general", "en", "au")
            , new NewsSource("google-news-br", "Google News (Brasil)", "https://news.google.com", "general", "pt", "br")
            , new NewsSource("google-news-ca", "Google News (Canada)", "https://news.google.com", "general", "en", "ca")
            , new NewsSource("google-news-fr", "Google News (France)", "https://news.google.com", "general", "fr", "fr")
            , new NewsSource("google-news-in", "Google News (India)", "https://news.google.com", "general", "en", "in")
            , new NewsSource("google-news-is", "Google News (Israel)", "https://news.google.com", "general", "he", "is")
            , new NewsSource("google-news-it", "Google News (Italy)", "https://news.google.com", "general", "it", "it")
            , new NewsSource("google-news-ru", "Google News (Russia)", "https://news.google.com", "general", "ru", "ru")
            , new NewsSource("google-news-sa", "Google News (Saudi Arabia)", "https://news.google.com", "general", "ar", "sa")
            , new NewsSource("google-news-uk", "Google News (UK)", "https://news.google.com", "general", "en", "gb")
            , new NewsSource("goteborgs-posten", "GÃ¶teborgs-Posten", "http://www.gp.se", "general", "se", "se")
            , new NewsSource("gruenderszene", "Gruenderszene", "http://www.gruenderszene.de", "technology", "de", "de")
            , new NewsSource("hacker-news", "Hacker News", "https://news.ycombinator.com", "technology", "en", "us")
            , new NewsSource("handelsblatt", "Handelsblatt", "http://www.handelsblatt.com", "business", "de", "de")
            , new NewsSource("ign", "IGN", "http://www.ign.com", "entertainment", "en", "us")
            , new NewsSource("il-sole-24-ore", "Il Sole 24 Ore", "http://www.ilsole24ore.com", "business", "it", "it")
            , new NewsSource("independent", "Independent", "http://www.independent.co.uk", "general", "en", "gb")
            , new NewsSource("infobae", "Infobae", "http://www.infobae.com/?noredirect", "general", "es", "ar")
            , new NewsSource("info-money", "InfoMoney", "http://www.infomoney.com.br", "business", "pt", "br")
            , new NewsSource("la-gaceta", "La Gaceta", "http://www.lagaceta.com.ar", "general", "es", "ar")
            , new NewsSource("la-nacion", "La Nacion", "http://www.lanacion.com.ar", "general", "es", "ar")
            , new NewsSource("la-repubblica", "La Repubblica", "http://www.repubblica.it", "general", "it", "it")
            , new NewsSource("le-monde", "Le Monde", "http://www.lemonde.fr", "general", "fr", "fr")
            , new NewsSource("lenta", "Lenta", "https://lenta.ru", "general", "ru", "ru")
            , new NewsSource("lequipe", "L'equipe", "https://www.lequipe.fr", "sports", "fr", "fr")
            , new NewsSource("les-echos", "Les Echos", "https://www.lesechos.fr", "business", "fr", "fr")
            , new NewsSource("liberation", "LibÃ©ration", "http://www.liberation.fr", "general", "fr", "fr")
            , new NewsSource("marca", "Marca", "http://www.marca.com", "sports", "es", "es")
            , new NewsSource("mashable", "Mashable", "http://mashable.com", "entertainment", "en", "us")
            , new NewsSource("medical-news-today", "Medical News Today", "http://www.medicalnewstoday.com", "health", "en", "us")
            , new NewsSource("metro", "Metro", "http://metro.co.uk", "general", "en", "gb")
            , new NewsSource("mirror", "Mirror", "http://www.mirror.co.uk/", "general", "en", "gb")
            , new NewsSource("msnbc", "MSNBC", "http://www.msnbc.com", "general", "en", "us")
            , new NewsSource("mtv-news", "MTV News", "http://www.mtv.com/news", "entertainment", "en", "us")
            , new NewsSource("mtv-news-uk", "MTV News (UK)", "http://www.mtv.co.uk/news", "entertainment", "en", "gb")
            , new NewsSource("national-geographic", "National Geographic", "http://news.nationalgeographic.com", "science", "en", "us")
            , new NewsSource("national-review", "National Review", "https://www.nationalreview.com/", "general", "en", "us")
            , new NewsSource("nbc-news", "NBC News", "http://www.nbcnews.com", "general", "en", "us")
            , new NewsSource("news24", "News24", "http://www.news24.com", "general", "en", "za")
            , new NewsSource("new-scientist", "New Scientist", "https://www.newscientist.com/section/news", "science", "en", "us")
            , new NewsSource("news-com-au", "News.com.au", "http://www.news.com.au", "general", "en", "au")
            , new NewsSource("newsweek", "Newsweek", "http://www.newsweek.com", "general", "en", "us")
            , new NewsSource("new-york-magazine", "New York Magazine", "http://nymag.com", "general", "en", "us")
            , new NewsSource("next-big-future", "Next Big Future", "https://www.nextbigfuture.com", "science", "en", "us")
            , new NewsSource("nfl-news", "NFL News", "http://www.nfl.com/news", "sports", "en", "us")
            , new NewsSource("nhl-news", "NHL News", "https://www.nhl.com/news", "sports", "en", "us")
            , new NewsSource("nrk", "NRK", "https://www.nrk.no", "general", "no", "no")
            , new NewsSource("politico", "Politico", "https://www.politico.com", "general", "en", "us")
            , new NewsSource("polygon", "Polygon", "http://www.polygon.com", "entertainment", "en", "us")
            , new NewsSource("rbc", "RBC", "http://www.rbc.ru", "general", "ru", "ru")
            , new NewsSource("recode", "Recode", "http://www.recode.net", "technology", "en", "us")
            , new NewsSource("reddit-r-all", "Reddit /r/all", "https://www.reddit.com/r/all", "general", "en", "us")
            , new NewsSource("reuters", "Reuters", "http://www.reuters.com", "general", "en", "us")
            , new NewsSource("rt", "RT", "https://russian.rt.com", "general", "ru", "ru")
            , new NewsSource("rte", "RTE", "https://www.rte.ie/news", "general", "en", "ie")
            , new NewsSource("rtl-nieuws", "RTL Nieuws", "https://www.rtlnieuws.nl/", "general", "nl", "nl")
            , new NewsSource("sabq", "SABQ", "https://sabq.org", "general", "ar", "sa")
            , new NewsSource("spiegel-online", "Spiegel Online", "http://www.spiegel.de", "general", "de", "de")
            , new NewsSource("svenska-dagbladet", "Svenska Dagbladet", "https://www.svd.se", "general", "se", "se")
            , new NewsSource("t3n", "T3n", "http://t3n.de", "technology", "de", "de")
            , new NewsSource("talksport", "TalkSport", "http://talksport.com", "sports", "en", "gb")
            , new NewsSource("techcrunch", "TechCrunch", "https://techcrunch.com", "technology", "en", "us")
            , new NewsSource("techcrunch-cn", "TechCrunch (CN)", "https://techcrunch.cn", "technology", "zh", "zh")
            , new NewsSource("techradar", "TechRadar", "http://www.techradar.com", "technology", "en", "us")
            , new NewsSource("the-american-conservative", "The American Conservative", "http://www.theamericanconservative.com/", "general", "en", "us")
            , new NewsSource("the-globe-and-mail", "The Globe And Mail", "https://www.theglobeandmail.com", "general", "en", "ca")
            , new NewsSource("the-hill", "The Hill", "http://thehill.com", "general", "en", "us")
            , new NewsSource("the-hindu", "The Hindu", "http://www.thehindu.com", "general", "en", "in")
            , new NewsSource("the-huffington-post", "The Huffington Post", "http://www.huffingtonpost.com", "general", "en", "us")
            , new NewsSource("the-irish-times", "The Irish Times", "https://www.irishtimes.com", "general", "en", "ie")
            , new NewsSource("the-jerusalem-post", "The Jerusalem Post", "https://www.jpost.com/", "general", "en", "is")
            , new NewsSource("the-lad-bible", "The Lad Bible", "http://www.theladbible.com", "entertainment", "en", "gb")
            , new NewsSource("the-new-york-times", "The New York Times", "http://www.nytimes.com", "general", "en", "us")
            , new NewsSource("the-next-web", "The Next Web", "http://thenextweb.com", "technology", "en", "us")
            , new NewsSource("the-sport-bible", "The Sport Bible", "http://www.thesportbible.com", "sports", "en", "gb")
            , new NewsSource("the-telegraph", "The Telegraph", "http://www.telegraph.co.uk", "general", "en", "gb")
            , new NewsSource("the-times-of-india", "The Times of India", "http://timesofindia.indiatimes.com", "general", "en", "in")
            , new NewsSource("the-verge", "The Verge", "http://www.theverge.com", "technology", "en", "us")
            , new NewsSource("the-wall-street-journal", "The Wall Street Journal", "http://www.wsj.com", "business", "en", "us")
            , new NewsSource("the-washington-post", "The Washington Post", "https://www.washingtonpost.com", "general", "en", "us")
            , new NewsSource("the-washington-times", "The Washington Times", "https://www.washingtontimes.com/", "general", "en", "us")
            , new NewsSource("time", "Time", "http://time.com", "general", "en", "us")
            , new NewsSource("usa-today", "USA Today", "http://www.usatoday.com/news", "general", "en", "us")
            , new NewsSource("vice-news", "Vice News", "https://news.vice.com", "general", "en", "us")
            , new NewsSource("wired", "Wired", "https://www.wired.com", "technology", "en", "us")
            , new NewsSource("wired-de", "Wired.de", "https://www.wired.de", "technology", "de", "de")
            , new NewsSource("wirtschafts-woche", "Wirtschafts Woche", "http://www.wiwo.de", "business", "de", "de")
            , new NewsSource("xinhua-net", "Xinhua Net", "http://xinhuanet.com/", "general", "zh", "zh")
            , new NewsSource("ynet", "Ynet", "http://www.ynet.co.il", "general", "he", "is")

    ).stream().collect(Collectors.toMap(NewsSource::getId, v -> v));

    private Set<String> languages = newsSourceMap.values().stream().map(s -> s.getLanguage()).collect(Collectors.toSet());

    public NewsSource getNewsSource(String id) {
        return newsSourceMap.get(id);
    }

    public String getNewsLanguage(News news) {
        NewsSource newsSource = newsSourceMap.get(news.getSource());
        return newsSource == null ? "no" : newsSource.getLanguage();
    }

    public Collection<NewsSource> getNewsSources() {
        return newsSourceMap.values();
    }

    public Set<String> supportedLanguages() {
        return new HashSet<>(languages);
    }
}
