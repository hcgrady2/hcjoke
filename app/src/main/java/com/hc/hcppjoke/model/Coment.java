package com.hc.hcppjoke.model;

/**
 * Created by hcw  on 2020/6/11
 * 类描述：
 * all rights reserved
 */
public class Coment {

    /**
     * id : 2025
     * itemId : 6832547562587691000
     * commentId : 6832604006670408000
     * userId : 0
     * commentType : 2
     * createTime : 1590839589
     * commentCount : 60
     * likeCount : 4916
     * commentText :
     * imageUrl : https://p1-ppx.byteimg.com/img/tos-cn-i-0000/99f20f68e9274c8e950caaed7784845d~960x1280.jpeg
     * videoUrl : null
     * width : 0
     * height : 0
     * hasLiked : false
     * author : {"id":1755,"userId":1578919786,"name":"、蓅哖╰伊人为谁笑","avatar":"http://qzapp.qlogo.cn/qzapp/101794421/FE41683AD4ECF91B7736CA9DB8104A5C/100","description":"小朋友,你是否有很多问号","likeCount":7,"topCommentCount":10,"followCount":102,"followerCount":23,"qqOpenId":"FE41683AD4ECF91B7736CA9DB8104A5C","expires_time":1596726031266,"score":1000,"historyCount":1453,"commentCount":123,"favoriteCount":11,"feedCount":10,"hasFollow":false}
     * ugc : {"likeCount":11,"shareCount":0,"commentCount":0,"hasFavorite":false,"hasLiked":false,"hasdiss":false,"hasDissed":false}
     */

    private int id;
    private long itemId;
    private long commentId;
    private int userId;
    private int commentType;
    private int createTime;
    private int commentCount;
    private int likeCount;
    private String commentText;
    private String imageUrl;
    private Object videoUrl;
    private int width;
    private int height;
    private boolean hasLiked;
    private User author;
    private Ugc ugc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Ugc getUgc() {
        return ugc;
    }

    public void setUgc(Ugc ugc) {
        this.ugc = ugc;
    }



}
