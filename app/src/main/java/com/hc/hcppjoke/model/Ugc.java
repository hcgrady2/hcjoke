package com.hc.hcppjoke.model;

/**
 * Created by hcw  on 2020/6/11
 * 类描述：
 * all rights reserved
 */
public class Ugc {
    private int likeCount;
    private int shareCount;
    private int commentCount;
    private boolean hasFavorite;
    private boolean hasLiked;
    private boolean hasdiss;
    private boolean hasDissed;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isHasFavorite() {
        return hasFavorite;
    }

    public void setHasFavorite(boolean hasFavorite) {
        this.hasFavorite = hasFavorite;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public boolean isHasdiss() {
        return hasdiss;
    }

    public void setHasdiss(boolean hasdiss) {
        this.hasdiss = hasdiss;
    }

    public boolean isHasDissed() {
        return hasDissed;
    }

    public void setHasDissed(boolean hasDissed) {
        this.hasDissed = hasDissed;
    }
}
