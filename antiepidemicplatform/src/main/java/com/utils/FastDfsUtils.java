package com.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastDfsUtils {


    public static List<UploadResult> uploadFiles(MultipartFile[] attachs)  {
        ArrayList<UploadResult> list = new ArrayList<>();
        Arrays.stream(attachs).forEach(file->{
            UploadResult res = upload(file);
            list.add(res);
        });
        return list ;
    }

    public  static  UploadResult upload(MultipartFile attach){
        String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".")+1);
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("fileName", attach.getOriginalFilename());
        meta_list[1] = new NameValuePair("fileLength", String.valueOf(attach.getSize()));
        meta_list[2] = new NameValuePair("fileExt", ext);
        meta_list[3] = new NameValuePair("fileAuthor", "");
        UploadResult uploadResult = null;
        try {
            uploadResult = FileUpload.uploadImgReturnUrls(attach.getBytes(), ext, meta_list);
            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResult;
    }

    /**
     * upload 视频
     */

    public  static  String uploadVideo(MultipartFile attach){
        String ext = attach.getOriginalFilename().substring(attach.getOriginalFilename().lastIndexOf(".")+1);
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("fileName", attach.getOriginalFilename());
        meta_list[1] = new NameValuePair("fileLength", String.valueOf(attach.getSize()));
        meta_list[2] = new NameValuePair("fileExt", ext);
        meta_list[3] = new NameValuePair("fileAuthor", "");
        String url = null;
        try {
             url  = FileUpload.uploadVideo(attach.getBytes(), ext, meta_list);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 批量上传视频
     *
     */
    public static List<String> uploadVideos(MultipartFile[] attachs)  {
        ArrayList<String> list = new ArrayList<>();
        Arrays.stream(attachs).forEach(file->{
            String res = uploadVideo(file);
            list.add(res);
        });
        return list ;
    }

   

}
