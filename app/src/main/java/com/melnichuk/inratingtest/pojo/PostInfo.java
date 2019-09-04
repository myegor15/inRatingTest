package com.melnichuk.inratingtest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostInfo {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("views_count")
    @Expose
    private String viewsCount;

    @SerializedName("likes_count")
    @Expose
    private String likesCount;

    @SerializedName("comments_count")
    @Expose
    private String commentsCount;

    @SerializedName("reposts_count")
    @Expose
    private String repostsCount;

    @SerializedName("bookmarks_count")
    @Expose
    private String bookmarksCount;

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(String repostsCount) {
        this.repostsCount = repostsCount;
    }

    public String getBookmarksCount() {
        return bookmarksCount;
    }

    public void setBookmarksCount(String bookmarksCount) {
        this.bookmarksCount = bookmarksCount;
    }

    public PostInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(String viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }
}