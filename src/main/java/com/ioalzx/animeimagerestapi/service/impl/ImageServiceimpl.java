package com.ioalzx.animeimagerestapi.service.impl;

import com.ioalzx.animeimagerestapi.entity.Image;
import com.ioalzx.animeimagerestapi.mapper.ImageMapper;
import com.ioalzx.animeimagerestapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceimpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Image findOne(String dhash) {
        return imageMapper.findOne(dhash);
    }

    @Override
    public Image getRandom() {
        return imageMapper.getRandom();
    }
}
