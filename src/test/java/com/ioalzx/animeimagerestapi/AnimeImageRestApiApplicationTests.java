package com.ioalzx.animeimagerestapi;

import com.ioalzx.animeimagerestapi.entity.ImageQueryParams;
import com.ioalzx.animeimagerestapi.util.TagTranslation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootTest
class AnimeImageRestApiApplicationTests {

    @Autowired
    TagTranslation tagTranslation;

    @Test
    void contextLoads() {
        var a = new ImageQueryParams("Anime", "100:200", "1080:", "200:", "1256", "(saber(o)rem)(a)!water", "mobile", false, tagTranslation);
        System.out.println(a.getTagList().toString());
    }

    @Test
    void webclient() {
        WebClient webClient = WebClient.create();
        Mono<Resource> mono = webClient.get().uri("https://images.ioalzx.site/Anime/c3e3b0bb67733a3e.jpg").retrieve().bodyToMono(Resource.class);
        Resource resource = mono.block();
        System.out.println(mono.block());
    }

    @Test
    void tagTrans() throws  Exception{
        String[] tags = {"天气", "海", "moon", "手机", "雨", "食物", "树木", "鼠标"};
        String[] resultTags = new String[8];
        List<Future<String>> results = new ArrayList<>();
        int count = 0;
        for ( String tag: tags) {
            if (  count == 5) {
                count = 0;
                Thread.sleep(2000);
            }
             results.add(tagTranslation.getEn(tag));
            count ++;
        }
        for ( var result : results ) {
            System.out.println(result.get().toLowerCase());
        }
    }

    @Test
    void regTest() {
        System.out.println("asdf".matches("[a-zA-Z]+"));
    }

}
