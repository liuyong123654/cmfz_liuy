package com.baizhi.service;

import com.baizhi.dto.StuPageDto;
import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselService {
    //查所有
    public List<Carousel> queryAll();
    //添加
    public String insertByCarousel(Carousel carousel);
    //分页
    public StuPageDto queryPage(Integer page,Integer rows);
    //修改图片路经
    public void updatePaths(Carousel carousel);
    //根据id删除
    public void deleteById(String carouselId);
    //根据id修改
    public void updateById(Carousel carousel);
    //根据id批量删除
    public void deleteByIds(String[] carouselIds);
}
