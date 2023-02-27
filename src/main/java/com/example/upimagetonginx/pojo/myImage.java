package com.example.upimagetonginx.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//私有变量get和set方法
@Data
//有参构造
@AllArgsConstructor
//无参构造
@NoArgsConstructor
public class myImage {
    private int id;
    private String url;
    private int userId;

//private String data 时间戳属性，数据库自增，，不用操作，不用定义
//但是拿出来的时候需要一个变量接着
    private String data;

}
