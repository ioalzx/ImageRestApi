package com.ioalzx.animeimagerestapi;

import com.ioalzx.animeimagerestapi.entity.ImageQueryParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnimeImageRestApiApplicationTests {

    @Test
    void contextLoads() {
        var a = new ImageQueryParams("Anime", "100:200", "1080:", "200:", "1256", "(saber(o)rem)(a)!water", "mobile", false);
        System.out.println(a.getTagList().toString());
    }

}
