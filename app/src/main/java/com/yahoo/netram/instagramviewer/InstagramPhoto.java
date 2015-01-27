package com.yahoo.netram.instagramviewer;

/**
 * Created by netram on 1/25/15.
 */
public class InstagramPhoto {

    private String userName;
    private String caption;
    private String imageUrl;
    private int height;
    private int numLikes;
    private String userImageUrl;
    private long createTime;

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;

    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "InstagramPhoto{" +
                "userName='" + userName + '\'' +
                ", caption='" + caption + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", height=" + height +
                ", numLikes=" + numLikes +
                ", userImageUrl=" + userImageUrl +
                ", createTime=" + createTime +
                '}';
    }
}
