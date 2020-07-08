package com.itheima.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {
    //------------------------------------ 文件上传 ------------------------------------

    /**
     * 传统方式-文件上传
     */
    @RequestMapping(value = "/testUpload1", method = RequestMethod.POST)
    public String testUpload1(HttpServletRequest request) throws Exception {
        //获取文件上传根目录
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //创建文件夹
        File pathDir = new File(path);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }
        //创建文件工厂
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析请求中的文件项
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                //只是普通表单项，不是文件，忽略
            } else {
                //是文件，写文件
                String fileName = item.getName();
                //生成唯一id
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = fileName + "_" + uuid;
                item.write(new File(pathDir, fileName));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }

    /**
     * SpringMVC方式-文件上传
     *
     * @param upload 多文件，变量名要和前端文件上传表单元素的name属性一致才行
     */
    @RequestMapping(value = "/testUpload2", method = RequestMethod.POST)
    public String testUpload2(HttpServletRequest request, @RequestParam("upload") MultipartFile upload) throws Exception {
        System.out.println("SpringMVC方式文件上传...");
        //获取文件上传路径，并创建目录
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File pathDir = new File(path);
        if (!pathDir.exists()) {
            pathDir.mkdirs();
        }
        //重命名文件名
        String originalFilename = upload.getOriginalFilename();
        //生成唯一id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = originalFilename + "_" + uuid;
        //上传文件
        upload.transferTo(new File(pathDir, fileName));
        return "success";
    }

    /**
     * 跨服务器方式-文件上传
     *
     * @param upload 多文件，变量名要和前端文件上传表单元素的name属性一致才行
     */
    @RequestMapping(value = "/testUpload3", method = RequestMethod.POST)
    public String testUpload3(@RequestParam("upload") MultipartFile upload) throws Exception {
        System.out.println("跨服务器方式文件上传...");
        //文件服务器上传文件请求路径
        String uploadPath = "http://localhost:9090/file_upload/uploads/";
        //重命名文件名
        String originalFilename = upload.getOriginalFilename();
        //生成唯一id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = originalFilename + "_" + uuid;
        //创建客户端对象
        Client client = Client.create();
        WebResource webResource = client.resource(uploadPath + fileName);
        //上传文件
        webResource.put(upload.getBytes());
        return "success";
    }
}