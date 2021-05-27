package com.ioalzx.animeimagerestapi.service;

import com.ioalzx.animeimagerestapi.entity.Image;

public interface ImageService {

    Image findOne( String dhash );

    Image getRandom ();

}
