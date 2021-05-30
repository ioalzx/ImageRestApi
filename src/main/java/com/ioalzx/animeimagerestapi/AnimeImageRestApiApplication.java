package com.ioalzx.animeimagerestapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author timxzhao
 */
@SpringBootApplication
@MapperScan("com.ioalzx.animeimagerestapi.mapper")
public class AnimeImageRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimeImageRestApiApplication.class, args);
    }

}
