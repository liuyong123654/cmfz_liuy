package com.baizhi.controller;


import com.baizhi.dao.AlbummDao;
import com.baizhi.dto.ChapterDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService service;
    @Autowired
    private AlbummDao daos;
    @RequestMapping("query")
    public ChapterDto edit(Integer page,Integer rows,String albumId){
        ChapterDto dto = service.queryByPage(page, rows,albumId);
        return dto;
    }
    @RequestMapping("edit")
    public String edit(String oper, Chapter chapter, String[] id,String albumId){
        if("add".equals(oper)){
            Album album = new Album();
            album.setAlbumId(albumId);
            String s = service.insertById(chapter);
            int i = daos.selectById(albumId);
            album.setCount(i+1);
            daos.updateById(album);
            return s;
        }else if ("del".equals(oper)){
            service.deleteById(id[0]);

        }else {
            chapter.setChapterId(id[0]);
            service.updateById(chapter);
            return chapter.getChapterId();
        }if (id.length>1){
            for (String s : id) {
                service.deleteById(s);
            }
        }


        return null;
    }
    @RequestMapping("upload")
    public void upload(String chapterId, MultipartFile downPath, HttpServletRequest request, HttpServletResponse response,String albumId){
        String originalFilename = downPath.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("music");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            downPath.transferTo(new File(path,originalFilename));
            //修改数据库的路径
            Chapter chapter = new Chapter();
            chapter.setChapterId(chapterId);
            chapter.setDownPath(originalFilename);
            service.modifyDownpath(chapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("download")
    public void download(String downPath,HttpServletRequest request,HttpServletResponse response)throws Exception{
        String path = request.getSession().getServletContext().getRealPath("music");
        //获取当前文件
        File file = new File(path, downPath);
        String substring = downPath.substring(downPath.lastIndexOf("."));
        //设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType(substring));
        //设置响应头
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(downPath,"UTF-8"));
        //将文件响应到浏览器
        FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
    }
}
