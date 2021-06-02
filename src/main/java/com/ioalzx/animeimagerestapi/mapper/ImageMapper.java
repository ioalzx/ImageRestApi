package com.ioalzx.animeimagerestapi.mapper;

import com.ioalzx.animeimagerestapi.entity.Image;
import com.ioalzx.animeimagerestapi.entity.ImageQueryParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {
    Image findOne(@Param("dhash") String dhash);

    Image getRandom();

    Image findRandom(@Param("qp") ImageQueryParams queryParams);
}
