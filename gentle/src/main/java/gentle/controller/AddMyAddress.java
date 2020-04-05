package gentle.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import gentle.base.ReturnMsg;

@Controller
@RequestMapping(value = "/imgs",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
public class AddMyAddress {

@RequestMapping("/imgUpload")
@ResponseBody	
public String imgUpload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request){

    //获取文件在服务器的储存位置
    String path = request.getSession().getServletContext().getRealPath("/upload");
    File filePath = new File(path);
    System.out.println("文件的保存路径："+filePath);
    if(!filePath.exists() && !filePath.isDirectory()){
        System.out.println("目录不存在");
        filePath.mkdir();
    }


    //获取原始文件名称(包含格式)
    String originalFilename = picture.getOriginalFilename();
    System.out.println("原始文件的名称是："+originalFilename);

    //获取文件类型，以最后一个`.`为标识
    String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    System.out.println("文件类型："+type);
    //获取文件名称（不包含格式）
    String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));

    //设置文件新名称: 当前时间+文件名称（不包含格式）
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String date = sdf.format(d);
    String fileName = date + "." + type;
    System.out.println("新文件名称：" + fileName);

    //在指定路径下创建一个文件
    File targetFile = new File(path, fileName);
    //将文件保存到服务器指定位置

    try {
        picture.transferTo(targetFile);
        System.out.println("上传成功");
        //将文件在服务器的存储路径返回
        ReturnMsg returnMsg = new ReturnMsg("200",fileName);
       return JSON.toJSONString(returnMsg);
    } catch (IOException e) {
        System.out.println("上传失败");
        e.printStackTrace();
        ReturnMsg returnMsg = new ReturnMsg("400","上传失败");
        return JSON.toJSONString(returnMsg);
    }

}

}