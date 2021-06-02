package com.ioalzx.animeimagerestapi.entity;

public class Image {
    private String dhash;
    private String cat;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer rating;
    private String tags;

    public Image(String dhash, String cat, Integer width, Integer height, Integer size, Integer rating, String tags) {
        this.dhash = dhash;
        this.cat = cat;
        this.width = width;
        this.height = height;
        this.size = size;
        this.rating = rating;
        this.tags = tags;
    }

    public String getDhash() {
        return dhash;
    }

    public void setDhash(String dhash) {
        this.dhash = dhash;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
