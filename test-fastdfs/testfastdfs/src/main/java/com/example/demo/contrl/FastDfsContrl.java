package com.example.demo.contrl;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xulei on 2019/8/7.
 */
@Controller
public class FastDfsContrl {

    @Autowired
    private FastFileStorageClient storageClient;

    @RequestMapping("/index")
    public String index(){
        return "upload";
    }
    @RequestMapping("/toTurnTable")
    public String toTurnTable(){return "turntable";}
    @PostMapping("myUpload")
    @ResponseBody
    public String testUpload(MultipartFile myFile) {
        // myFile.getOriginalFilename():取到文件的名字
        // FilenameUtils.getExtension(""):取到一个文件的后缀名
        String extension = FilenameUtils.getExtension(myFile.getOriginalFilename());

        // group1:指storage服务器的组名
        // myFile.getInputStream():指这个文件中的输入流
        // myFile.getSize():文件的大小
        // 这一行是通过storageClient将文件传到storage容器
        StorePath uploadFile = null;
        try {
            uploadFile = storageClient.uploadFile("group1", myFile.getInputStream(), myFile.getSize(), extension);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 上传数据库
//        String sql = "insert into file(filename,groupname,filepath) values(?,?,?)";
//        jdbcTemplate.update(sql, myFile.getOriginalFilename(), uploadFile.getGroup(), uploadFile.getPath());

        // 返回它在storage容器的的路径
        return uploadFile.getFullPath();
    }
    @GetMapping("/fdownload")
    public void download(HttpServletResponse response) throws IOException {

//        List query = jdbcTemplate.query("select * from file where fileid=" + id, new ColumnMapRowMapper());
//        Map map = (Map) query.get(0);
//        String filename = URLEncoder.encode(map.get("filename").toString(), "utf-8"); // 解决中文文件名下载后乱码的问题
        // 告诉浏览器 下载的文件名
        response.setHeader("Content-Disposition", "attachment; filename=微信图片_20190807174709.png");
//        String groupName = map.get("groupName").toString();
//        String filepath = map.get("filepath").toString();
//        // 将文件的内容输出到浏览器 fastdfs
        byte[] downloadFile = storageClient.downloadFile("group1", "/M00/00/00/wKg4IV1SMluAEFbMAB4-5aQGzJw775.png");
        response.getOutputStream().write(downloadFile);

    }

}
