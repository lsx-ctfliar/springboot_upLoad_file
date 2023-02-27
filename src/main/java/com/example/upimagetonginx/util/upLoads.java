package com.example.upimagetonginx.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class upLoads {

//    //tomcat服务器上面的路径，作为我们上传图片的路径 /usr/tomcat/apache-tomcat-9.0.69/webapps/uploads/
//    @Value("${files.upload.path}")
//    private String upImagePath;
//
//    //服务器返回图片地址
//    @Value("${files2.get.path}")
//    private String getImagePath;



//    @value注解不能局部定义，，在别的类里面调用上传的图片的方法，在这里取到的路径，，没有到配置文件取值，所以一直为空
//

//    public String upLoad(MultipartFile file){
//
//        //得到上传图片的名称  ,getName()获取的是代码里的变量名字
//        String filename = file.getOriginalFilename();
//        System.out.println("上传图片的名字："+filename);
//
//        //上传的图片通过当前时间戳+原来图片的拓展名进行重命名，防止相同的名字覆盖图片
//        String filename1 = System.currentTimeMillis()+"."+filename.substring(filename.lastIndexOf(".")+1);
//
//        System.out.println("时间戳加扩展名命名："+filename1);
//        //在服务器路径上新建文件对象
//        File image = new File(upImagePath,filename1);
//
//        //往数据流写入文件对象
//        try{
//            file.transferTo(image);
//        }catch (IOException e){
//            e.printStackTrace();
////            如果写入失败直接返回空
//            return null;
//        }
//
////        写入成功，返回获取图片的路径
//        System.out.println("上传路径位置："+upImagePath);
//        System.out.println("服务器图片前缀路径："+getImagePath);
//        return getImagePath+filename1;
//    }



/**
 *
 * 修改该上传函数，，接受第三方类 取到的上传路径
 * @param file
 * @param upImagePath
 * @param getImagePath
 *
 * */

public String upLoad(MultipartFile file,String upImagePath,String getImagePath){

    //得到上传图片的名称  ,getName()获取的是代码里的变量名字
    String filename = file.getOriginalFilename();
    System.out.println("上传图片的名字："+filename);

    //上传的图片通过当前时间戳+原来图片的拓展名进行重命名，防止相同的名字覆盖图片
    String filename1 = System.currentTimeMillis()+"."+filename.substring(filename.lastIndexOf(".")+1);

    System.out.println("时间戳加扩展名命名："+filename1);
    //在服务器路径上新建文件对象
    File image = new File(upImagePath,filename1);

    if(!image.exists()){
        image.mkdirs();    //如果该路径不存在就新建该路径
    }
    //往数据流写入文件对象
    try{
        file.transferTo(image);
    }catch (IOException e){
        e.printStackTrace();
//            如果写入失败直接返回空
        return null;
    }

//        写入成功，返回获取图片的路径
    System.out.println("上传路径位置："+upImagePath);
    System.out.println("服务器图片前缀路径："+getImagePath);
    return getImagePath+filename1;
}

}
