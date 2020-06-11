package com.hc.hcppjoke.model;

/**
 * Created by hcw  on 2020/6/11
 * 类描述：
 * all rights reserved
 */
public class User {


    /**
     * id : 1867
     * userId : 69756497446
     * name : 218723185
     * avatar : https://p1-ppx.byteimg.com/img/p1056/171f84f5c74740b996647695a1f39245~200x200.jpeg
     * description :
     * likeCount : 0
     * topCommentCount : 0
     * followCount : 0
     * followerCount : 6
     * qqOpenId : null
     * expires_time : 0
     * score : 0
     * historyCount : 0
     * commentCount : 0
     * favoriteCount : 0
     * feedCount : 0
     * hasFollow : false
     */

    private int id;
    private long userId;
    private String name;
    private String avatar;
    private String description;
    private int likeCount;
    private int topCommentCount;
    private int followCount;
    private int followerCount;
    private Object qqOpenId;
    private int expires_time;
    private int score;
    private int historyCount;
    private int commentCount;
    private int favoriteCount;
    private int feedCount;
    private boolean hasFollow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getTopCommentCount() {
        return topCommentCount;
    }

    public void setTopCommentCount(int topCommentCount) {
        this.topCommentCount = topCommentCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public Object getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(Object qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public int getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(int expires_time) {
        this.expires_time = expires_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHistoryCount() {
        return historyCount;
    }

    public void setHistoryCount(int historyCount) {
        this.historyCount = historyCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getFeedCount() {
        return feedCount;
    }

    public void setFeedCount(int feedCount) {
        this.feedCount = feedCount;
    }

    public boolean isHasFollow() {
        return hasFollow;
    }

    public void setHasFollow(boolean hasFollow) {
        this.hasFollow = hasFollow;
    }
}


