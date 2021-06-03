package com.ducnguyen46.covstats.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)
public class RssFeed implements Serializable {
    @Element
    private RssChannel channel;

    public RssChannel getChannel() {
        return channel;
    }

    public void setChannel(RssChannel channel) {
        this.channel = channel;
    }
}
