package com.ioalzx.animeimagerestapi.mapper;

import com.ioalzx.animeimagerestapi.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {
    Image findOne(@Param("dhash") String dhash);
    Image getRandom();
}
