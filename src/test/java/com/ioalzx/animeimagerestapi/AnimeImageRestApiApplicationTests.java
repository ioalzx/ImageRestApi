package com.ioalzx.animeimagerestapi;

import com.ioalzx.animeimagerestapi.entity.ImageQueryParams;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class AnimeImageRestApiApplicationTests {

    @Test
    void contextLoads() {
        var a = new ImageQueryParams("Anime", "100:200", "1080:", "200:", "1256", "(saber(o)rem)(a)!water", "mobile", false);
        System.out.println(a.getTagList().toString());
    }

    @Test
    void webclient() {
        WebClient webClient = WebClient.create();
        Mono<Resource> mono = webClient.get().uri("https://images.ioalzx.site/Anime/c3e3b0bb67733a3e.jpg").retrieve().bodyToMono(Resource.class);
        Resource resource = mono.block();
        System.out.println(mono.block());
    }

}
