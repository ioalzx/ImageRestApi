package com.ioalzx.animeimagerestapi.controller;

import com.ioalzx.animeimagerestapi.entity.Image;
import com.ioalzx.animeimagerestapi.entity.ImageWithTags;
import com.ioalzx.animeimagerestapi.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/image")
public class ImageGetterController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/getrandom")
    public String getRandom(HttpServletRequest httpServletRequest) {
        log.info( httpServletRequest.getRemoteAddr() + " accessed random image getter");
        return String.format("redirect:%s", new ImageWithTags(imageService.getRandom()).getUrl());
    }

    @GetMapping("/findrandom")
    public String findRandom(@RequestParam(value = "cat", required = false) String cat, @RequestParam(value = "w", required = false) String w, @RequestParam(value = "h", required = false) String h,
                                    @RequestParam(value = "fsize", required = false) String fsize, @RequestParam(value = "rating", required = false) String rating,
                                    @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "shape", required = false) String shape,
                                    @RequestParam(value = "trans", required = false) Boolean trans) {
        return String.format("redirect:%s", new ImageWithTags(imageService.findRandom(imageService.getImageQueryParams(cat, w, h , fsize, rating, tags, shape, trans ))).getUrl()) ;
    }

}
