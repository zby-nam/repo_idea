package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //分页查询所有广告信息
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> allAdByPage = promotionAdService.findAllAdByPage(promotionAdVo);
        ResponseResult result = new ResponseResult(true, 200, "广告分页查询成功", allAdByPage);
        return result;
    }
    //图片上传
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileupload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
        try {
            //1.判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            //2.获取项目部署路径
            String realPath = request.getServletContext().getRealPath("/");
            String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));
            // 3.获取原文件名
            String fileName = file.getOriginalFilename();
            //4.新文件名
            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
            //5.上传文件
            String uploadPath = webappsPath + "upload"; File filePath = new File(uploadPath, newFileName);
            //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath); }
            file.transferTo(filePath);
            //6.将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8080/upload/" + newFileName); ResponseResult result = new ResponseResult(true, 200, "响应成功",map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //新建&修改广告
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告成功",null);
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true,200,"查询成功",promotionAd);
        return result;
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, int status){
        if (status == 1){
            promotionAdService.updatePromotionAdStatus(id, status);
        } else {
            promotionAdService.updatePromotionAdStatus(id,0);
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"响应成功",map);
    }

}
