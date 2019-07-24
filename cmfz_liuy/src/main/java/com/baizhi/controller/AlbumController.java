package com.baizhi.controller;


import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService service;
    @RequestMapping("query")
    public AlbumDto queryByPage(Integer page,Integer rows){
        AlbumDto albumDto = service.queryAllPage(page, rows);
        return albumDto;
    }
    @RequestMapping("edit")
    public String edit(String oper, Album album,String[] id){
        if ("add".equals(oper)){
            String s = service.insertByAlbum(album);
            return s;
        }else if ("del".equals(oper)){
            service.deleteId(id[0]);
        }else {
            album.setAlbumId(id[0]);
            album.setCover(null);
            service.updateId(album);

            return album.getAlbumId();

        }if (id.length>0){
            for (String s : id) {
                service.deleteId(s);
            }
        }

        return null;
    }
    @RequestMapping("upload")
    public void upload(MultipartFile cover, HttpServletRequest request, String albumID, HttpServletResponse response){
        String originalFilename = cover.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("upload");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            cover.transferTo(new File(path,originalFilename));
            Album album = new Album();
            album.setAlbumId(albumID);
            album.setCover(originalFilename);

            service.updateBycover(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
