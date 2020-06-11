package com.hc.hcppjoke.model;

/**
 * Created by hcw  on 2020/6/11
 * 类描述：
 * all rights reserved
 */
public class Feed {

    /**
     * id : 1578919906
     * itemId : 6831909366883424000
     * itemType : 2
     * createTime : 1590677855
     * duration : 68.662
     * feeds_text : 难得在某音看到这种正能量的视频
     * authorId : 102985300487
     * activityIcon : null
     * activityText : null
     * width : 576
     * height : 1024
     * url : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6831909366883424515.mp4
     * cover : https://p1-ppx.byteimg.com/img/mosaic-legacy/30c0f00059327620262bc~576x1024_q80.jpeg
     */

    private int id;
    private long itemId;
    private int itemType;
    private int createTime;
    private double duration;
    private String feeds_text;
    private long authorId;
    private Object activityIcon;
    private Object activityText;
    private int width;
    private int height;
    private String url;
    private String cover;


    private User author;
    private Coment comment;
    private Ugc ugc;//点赞分享的数量。

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

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getFeeds_text() {
        return feeds_text;
    }

    public void setFeeds_text(String feeds_text) {
        this.feeds_text = feeds_text;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Object getActivityIcon() {
        return activityIcon;
    }

    public void setActivityIcon(Object activityIcon) {
        this.activityIcon = activityIcon;
    }

    public Object getActivityText() {
        return activityText;
    }

    public void setActivityText(Object activityText) {
        this.activityText = activityText;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Coment getComment() {
        return comment;
    }

    public void setComment(Coment comment) {
        this.comment = comment;
    }

    public Ugc getUgc() {
        return ugc;
    }

    public void setUgc(Ugc ugc) {
        this.ugc = ugc;
    }
}
