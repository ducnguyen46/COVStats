package com.ducnguyen46.covstats.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "channel", strict = false)
public class RssChannel {
    @ElementList(name = "item", inline = true)
    private ArrayList<RssItem> listItem;

    public ArrayList<RssItem> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<RssItem> listItem) {
        this.listItem = listItem;
    }
}
