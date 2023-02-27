package com.example.upimagetonginx.controller;


import com.example.upimagetonginx.mapper.imageMapper;
import com.example.upimagetonginx.pojo.myImage;
import com.example.upimagetonginx.util.JsonRestful;
import com.example.upimagetonginx.util.upLoads;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//lombok提供的注解，作用是注入的类直接用final声明就可以简化了 @Autowired
//通常用在需要注入的mapper service接口很多的情况下
@RequiredArgsConstructor
@RequestMapping(value = "/upFile")
@RestController
public class upLoadImageController extends baseController{

    //测试数据库连接
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    imageMapper imageMapper;

    //tomcat服务器上面的路径，作为我们上传图片的路径 /usr/tomcat/apache-tomcat-9.0.69/webapps/uploads/
    @Value("${files.upload.path}")
    private String upImagePath;

    //服务器返回图片地址
    @Value("${files2.get.path}")
    private String getImagePath;



    @RequestMapping(value = "/testDatabase",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonRestful testDatabase(){

        //
//        JsonRestful res = new JsonRestful();
//        return res;

        DataSource data = jdbcTemplate.getDataSource();

        return new JsonRestful(ok ,"数据库连接测试",data);
    };


//    private final UserMapper userMapper;  final关键字代替@Autowired


    /**
     * 上传图片接口
     *
     * */

    @RequestMapping(value = "/upImage",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonRestful upImage(MultipartFile file){

//        调用图片上传工具类，，返回上传成功后服务器的图片路径
        System.out.println("控制类里的上传路径"+upImagePath);
        String imagePath = new upLoads().upLoad(file,upImagePath,getImagePath);
        JsonRestful myRes = new JsonRestful();
//        JsonRestful res = new JsonRestful();   命名是res的时候报错

        System.out.println(imagePath);
        if(imagePath!=null){
                //封装返回数据
            myRes.setCode(ok);
            myRes.setMsg("上传成功");
            Map<String,String> data = new HashMap<>();
            data.put("url",imagePath);
            myRes.setObject(data);
                //将图片路径存入数据库中
            myImage image = new myImage();
            image.setUrl(imagePath);
            imageMapper.saveImageUrl(image);
        }
        else {
            myRes.setCode(error);
            myRes.setMsg("上传失败");
            Map<String,String> data = new HashMap<>();
            data.put("url",null);
            myRes.setObject(data);
        }


        return myRes;
    }



//
//    @ApiOperation("将图片访问路径存到数据库中")
//    @RequestMapping(value = "/addUserImage",method = RequestMethod.POST)
//    public CommonResult<Object> addImage(User user){
//        if(userMapper.updateById(user)>0){
//            return CommonResult.success(ResultCode.ADD_SUCCESS);
//        }
//        else{
//            return CommonResult.failed(ResultCode.FAILED);
//        }
//
//    }







}
