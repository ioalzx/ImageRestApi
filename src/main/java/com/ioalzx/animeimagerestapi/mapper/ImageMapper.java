package com.ioalzx.animeimagerestapi.mapper;

import com.ioalzx.animeimagerestapi.entity.Image;
import com.ioalzx.animeimagerestapi.entity.ImageQueryParams;
import com.mitchellbosecke.pebble.utils.Pair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ImageMapper {
    Image findOne(@Param("dhash") String dhash);
    Image getRandom();
    Image findRandom(@Param("qp") ImageQueryParams queryParams);
}
