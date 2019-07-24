package com.baizhi.controller;


import com.baizhi.dto.StuPageDto;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("carousel")
public class CarouselConproller {
    @Autowired
    private CarouselService service;

    public List<Carousel> query(){
        List<Carousel> list = service.queryAll();

        return list;
    }
    @RequestMapping("query")
    public StuPageDto queryBypage(Integer page,Integer rows){
        StuPageDto i = service.queryPage(page, rows);
        return i;
    }
    @RequestMapping("edit")
    public String edit(Carousel carousel,String oper,String[] id){
        if ("add".equals(oper)){
            String s = service.insertByCarousel(carousel);
            return s;
        }else if ("del".equals(oper)){
            service.deleteById(id[0]);

        }else {
            carousel.setCarouselId(id[0]);
            carousel.setImgPath(null);
            service.updateById(carousel);
            return carousel.getCarouselId();

        }if (id.length>0){
            for (String s : id) {
                service.deleteById(s);
            }
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(MultipartFile imgPath, HttpServletRequest request,String carouselId, HttpServletResponse response){
        String originalFilename = imgPath.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println(originalFilename);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            imgPath.transferTo(new File(path,originalFilename));
            Carousel carousel = new Carousel();
            carousel.setCarouselId(carouselId);
            carousel.setImgPath(originalFilename);
            System.out.println(carousel);
            service.updatePaths(carousel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
