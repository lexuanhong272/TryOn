package com.android.xuanhong.tryonglasses;

/**
 * Created by Pinky on 08-May-17.
 */

public class Glass {
    private String title;
    private String describe;
    private String price;
    private int poster;

    public Glass(String title, String describe, String price, int poster) {
        this.title = title;
        this.describe = describe;
        this.price = price;
        this.poster = poster;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public int getPoster() {
        return poster;
    }
}