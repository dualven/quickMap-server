package org.quickMap.dataService.dao.model;

import java.io.Serializable;

public class Novel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int novel_ID;

    private String novel_Url;

    private String novel_Author;

    private String novel_Name;

    private String novel_CoverURL;

    private int novel_Wordscount;

    private String novel_LatestUpdateTime;

    private String novel_Type;

    private String novel_Intro;
    private String novel_Isfinished;

    public String getNovel_Author() {
        return novel_Author;
    }

    public String getNovel_Isfinished() {
        return novel_Isfinished;
    }

    public void setNovel_Isfinished(String novel_Isfinished) {
        this.novel_Isfinished = novel_Isfinished;
    }

    public String getNovel_Intro() {
        return novel_Intro;
    }

    public void setNovel_Intro(String novel_Intro) {
        this.novel_Intro = novel_Intro;
    }

    public String getNovel_Name() {
        return novel_Name;
    }

    public void setNovel_Name(String novel_Name) {
        this.novel_Name = novel_Name;
    }

    public String getNovel_Type() {
        return novel_Type;
    }

    public void setNovel_Type(String novel_Type) {
        this.novel_Type = novel_Type;
    }

    public String getNovel_LatestUpdateTime() {
        return novel_LatestUpdateTime;
    }

    public void setNovel_LatestUpdateTime(String novel_LatestUpdateTime) {
        this.novel_LatestUpdateTime = novel_LatestUpdateTime;
    }

    public int getNovel_Wordscount() {
        return novel_Wordscount;
    }

    public void setNover_Wordscount(int novel_Wordscount) {
        this.novel_Wordscount = novel_Wordscount;
    }

    public String getnovel_CoverURL() {
        return novel_CoverURL;
    }

    public void setnovel_CoverURL(String novel_CoverUrl) {
        this.novel_CoverURL = novel_CoverUrl;
    }


    public String getNovel_Url() {
        return novel_Url;
    }

    public void setNovel_Url(String novel_Url) {
        this.novel_Url = novel_Url;
    }

    public int getNovel_ID() {
        return novel_ID;
    }

    public void setNovel_ID(int novel_ID) {
        this.novel_ID = novel_ID;
    }

    public void setNovel_Author(String novel_Author) {
        this.novel_Author = novel_Author;
    }

}