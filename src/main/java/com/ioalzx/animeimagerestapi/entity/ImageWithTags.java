package com.ioalzx.animeimagerestapi.entity;

public class ImageWithTags {
    private String dhash;
    private String cat;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer rating;
    private String[] tags;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageWithTags(Image img) {
        this.dhash = img.getDhash();
        this.cat = img.getCat();
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.size = img.getSize();
        this.rating = img.getRating();
        this.tags = img.getTags().split("\n");

        this.url = img.getRating() > 2 ? (String.format("https://d1xeww9d8c20o5.cloudfront.net/%s/%s.jpg", this.cat, this.dhash)) : (String.format("https://images.ioalzx.site/%s/%s.jpg", this.cat, this.dhash));
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
