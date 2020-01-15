package org.quickMap.dataService.dao.model;

import java.io.Serializable;

public class Chapter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2L;

    private int novel_ID;

    private int chapter_ID;

    private String chapter_Url;

    private String chapter_Content;

    private String chapter_Title;

    public int getNovel_ID() {
        return novel_ID;
    }

    public String getChapter_Title() {
        return chapter_Title;
    }

    public void setChapter_Title(String chapter_Title) {
        this.chapter_Title = chapter_Title;
    }

    public String getChapter_Content() {
        return chapter_Content;
    }

    public void setChapter_Content(String chapter_Content) {
        this.chapter_Content = chapter_Content;
    }

    public String getChapter_Url() {
        return chapter_Url;
    }

    public void setChapter_Url(String chapter_Url) {
        this.chapter_Url = chapter_Url;
    }

    public int getChapter_ID() {
        return chapter_ID;
    }

    public void setChapter_ID(int chapter_ID) {
        this.chapter_ID = chapter_ID;
    }

    public void setNovel_ID(int nover_ID) {
        this.novel_ID = nover_ID;
    }

}