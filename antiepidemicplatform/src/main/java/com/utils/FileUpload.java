package com.utils;

import net.coobird.thumbnailator.Thumbnails;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//文件上传工具类

@Component
@ConfigurationProperties(prefix = "dfs")

public class FileUpload {

    private   static  String prefix;

    private   static List<String> confs  = new ArrayList<>();


    @Value("${dfs.prefix}")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Value("${dfs.confs:120.78.137.116:22122}")
    public void setConfs(  List<String> confs) {
        this.confs = confs;
    }



    //    public static void main(String[]  args){
//
//         System.out.println( "prefix = =++"+ prefix);
//    }





    /**
     * 上传图片调用此方法返回原图和缩略图的url
     * @param fileContent 图片的byte数组
     * @param extName 图片后缀名
     * @param metas 图片说明数组
     * @return
     * @throws Exception
     */
    public static UploadResult uploadImgReturnUrls(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception{
        String url=null;
        String thumbnailUrl=null;
        UploadResult uploadResult = new UploadResult();
        url=uploadUrl(fileContent,extName,metas);
        thumbnailUrl=uploadThumbnailUrl(fileContent,extName,metas);
        uploadResult.setUrl(url);
        uploadResult.setThumbnailUrl(thumbnailUrl);
        return uploadResult;
    }

    /**
     * 上传视频文件
     * @param fileContent
     * @param extName
     * @param metas
     * @return
     * @throws Exception
     */

    public static String uploadVideo(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception{
//        ArrayList<String> confs = new ArrayList<>();
//        confs.add("120.78.137.116:22122");
        FastDFSClient fastDFSClient = new FastDFSClient(confs);
        String imgPath = fastDFSClient.uploadFile(fileContent,extName,metas);
        return prefix+imgPath;
    }




    public static String uploadUrl(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception{
        ByteArrayInputStream intputStream = new ByteArrayInputStream(fileContent);
        Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(intputStream).scale(1f);
        BufferedImage bufferedImage = builder.asBufferedImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, extName, baos);//extName:"png"
        byte[] byteArray = baos.toByteArray();
        FastDFSClient fastDFSClient = new FastDFSClient(confs);
        String imgPath = fastDFSClient.uploadFile(byteArray,extName,metas);
        return prefix+imgPath;
    }


    public static String uploadThumbnailUrl(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception{
        ByteArrayInputStream intputStream = new ByteArrayInputStream(fileContent);
        Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(intputStream).size(65, 40);
        BufferedImage bufferedImage = builder.asBufferedImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, extName, baos);//extName:"png"
        byte[] byteArray = baos.toByteArray();
        FastDFSClient fastDFSClient = new FastDFSClient(confs);
        String imgPath = fastDFSClient.uploadFile(byteArray,extName);
        return prefix +imgPath;
    }


    /**
     * 删除文件
     * @param group_name
     * @param remote_filename
     * */
    public static void delete(String group_name,String remote_filename){
        try {
            FastDFSClient fastDFSClient = new FastDFSClient(confs);
            fastDFSClient.delete(group_name, remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
