package com.ducnguyen46.covstats.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class RssItem {
    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "link")
    private String link;

    public String getImageURL() {
        Document document = Jsoup.parse(getDescription());
        String url = document.select("img").first().absUrl("src");
        return url;
    }

    public String getDes() {
        Document document = Jsoup.parse(getDescription());
        return document.text();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
