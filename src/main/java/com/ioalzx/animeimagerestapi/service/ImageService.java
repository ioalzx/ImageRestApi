package com.ioalzx.animeimagerestapi.service;

import com.ioalzx.animeimagerestapi.entity.Image;
import com.ioalzx.animeimagerestapi.entity.ImageQueryParams;

public interface ImageService {

    Image findOne( String dhash );

    Image getRandom ();

    Image findRandom(ImageQueryParams imageQueryParams);

    default ImageQueryParams getImageQueryParams(String cat, String w, String h, String fsize, String rating, String tags, String shape, Boolean trans) {
        return new ImageQueryParams(cat, w, h, fsize, rating, tags, shape, trans);
    }

}
