package com.baizhi.dao;

import com.baizhi.entity.Carousel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarouselDao {
    //查所有
    public List<Carousel> selectAll();
    //添加一个对象
    public void insetCarousel(Carousel carousel);
    //根据id删除
    public void deleteCarousel(String carouselId);
    //根据id修改
    public void updateCarousel(Carousel carousel);
    //根据id修改路经
    public void updatePath(Carousel carousel);
    //当页的数据行
    public List<Carousel> selectPage(@Param("Page")Integer Page,@Param("Size") Integer Size);
    //总行数
    public Integer selectTotalCount();
    //根据id批量删除
    public void deleteCarousels(String[] carouselId);
}
