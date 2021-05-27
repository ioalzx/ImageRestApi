package com.ioalzx.animeimagerestapi.controller;

import com.ioalzx.animeimagerestapi.entity.Image;
import com.ioalzx.animeimagerestapi.entity.ImageWithTags;
import com.ioalzx.animeimagerestapi.service.ImageService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/info")
public class ImageInfoController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/findbydhash")
    public ImageWithTags findOne(@RequestParam(value="dhash") String dhash) {
        return new ImageWithTags(imageService.findOne(dhash)) ;
    }

    @GetMapping("/getrandom")
    public ImageWithTags getRandom() {
        return new ImageWithTags(imageService.getRandom()) ;
    }

}
