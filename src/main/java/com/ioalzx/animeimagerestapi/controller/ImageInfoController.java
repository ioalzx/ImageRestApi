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

    @GetMapping("/findrandom")
    public ImageWithTags findRandom(@RequestParam(value = "cat", required = false) String cat, @RequestParam(value = "w", required = false) String w, @RequestParam(value = "h", required = false) String h,
                                    @RequestParam(value = "fsize", required = false) String fsize, @RequestParam(value = "rating", required = false) String rating,
                                    @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "shape", required = false) String shape,
                                    @RequestParam(value = "trans", required = false) Boolean trans) {
        return new ImageWithTags(imageService.findRandom(imageService.getImageQueryParams(cat, w, h , fsize, rating, tags, shape, trans ))) ;
    }

}
