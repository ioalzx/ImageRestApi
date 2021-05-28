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

}
