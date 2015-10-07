package edu.mobidev.labexambookmarks;

/**
 * Created by courtneyngo on 9/29/15.
 */
public class Bookmark {
    private String title;
    private String url;

    public Bookmark(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
