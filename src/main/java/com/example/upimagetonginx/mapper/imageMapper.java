package com.example.upimagetonginx.mapper;


import com.example.upimagetonginx.pojo.myImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface imageMapper {

//保存图片链接到数据库

    @Insert("insert into imageUrl(url,upData) values(#{url},now())")
    public int saveImageUrl(myImage image);

}
