package com.gdou.upload.web;

import com.gdou.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    //上传图片功能
    @PostMapping("image")
    public ResponseEntity<Map<String,Object>> uploadImage(@RequestParam("file")MultipartFile file){


        return  ResponseEntity.ok(uploadService.uploadImage(file));
    }

}
