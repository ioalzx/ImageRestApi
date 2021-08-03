package com.ioalzx.animeimagerestapi.entity;

import lombok.Data;

@Data
public class Image {
    private String dhash;
    private String cat;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer rating;
    private String tags;
}
