package com.ioalzx.animeimagerestapi.controller;

import com.ioalzx.animeimagerestapi.entity.ImageWithTags;
import com.ioalzx.animeimagerestapi.service.ImageService;
import com.ioalzx.animeimagerestapi.util.TagTranslation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Controller
@Slf4j
@RequestMapping("/image")
public class ImageGetterController {

    @Autowired
    TagTranslation tagTranslation;

    @Autowired
    private ImageService imageService;

    @GetMapping("/getrandom")
    public String getRandom(HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getRemoteAddr() + " accessed random image getter");
        return String.format("redirect:%s", new ImageWithTags(imageService.getRandom()).getUrl());
    }

    @GetMapping("/findrandom")
    public String findRandom(@RequestParam(value = "cat", required = false) String cat, @RequestParam(value = "w", required = false) String w, @RequestParam(value = "h", required = false) String h,
                             @RequestParam(value = "fsize", required = false) String fsize, @RequestParam(value = "rating", required = false) String rating,
                             @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "shape", required = false) String shape,
                             @RequestParam(value = "trans", required = false) String trans, @RequestParam(value = "get", required = false) String get,
                             @RequestParam(value = "func", required = false) String func, HttpServletResponse response) throws Exception {

        boolean transBoolean = "t".equals(trans);
        var image = new ImageWithTags(imageService.findRandom(imageService.getImageQueryParams(cat, w, h, fsize, rating, tags, shape, transBoolean, tagTranslation)));

        boolean aws = image.getRating() > 2;
        String suffix = aws ? "" : (func != null ? func : "");
        String url = image.getUrl() + suffix;

        if ("f".equals(get)) {
            response.setContentType("image/jpeg");
            OutputStream output = response.getOutputStream();
            WebClient webClient = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                    .codecs(configurer -> configurer
                            .defaultCodecs()
                            .maxInMemorySize(50 * 1024 * 1024))
                    .build()).build();
            Mono<Resource> mono = webClient.get().uri(url).retrieve().bodyToMono(Resource.class);
            Resource resource = mono.block();
            assert resource != null;
            resource.getInputStream().transferTo(output);
            output.flush();
            return null;


        } else {
            return String.format("redirect:%s", url);
        }

    }


}
