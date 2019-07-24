package com.baizhi.service;


import com.baizhi.dao.CarouselDao;
import com.baizhi.dto.StuPageDto;
import com.baizhi.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 轮播图的业务实现
 */
@Service
@Transactional
public class CarouselServiceImpl implements CarouselService{
    @Autowired
    CarouselDao dao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Carousel> queryAll() {
        List<Carousel> list = dao.selectAll();
        return list;
    }

    @Override
    public String insertByCarousel(Carousel carousel) {
        String s = UUID.randomUUID().toString();
        carousel.setCarouselId(s);
        dao.insetCarousel(carousel);
        return s;
    }

    @Override
    //分页查询(业务层调用dao,封装成StuPageDto对象返回到视图层)
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public StuPageDto queryPage(Integer page, Integer rows) {
        StuPageDto dto = new StuPageDto();
        dto.setPage(page);//当前页
        Integer totalCount = dao.selectTotalCount();
        Integer i = totalCount%rows == 0 ? totalCount/rows : totalCount/rows+1 ;
        dto.setTotal(i);//总页数
        dto.setRecords(totalCount);//总行数
        dto.setRows(dao.selectPage(page,rows));
        return dto;
    }

    @Override
    public void updatePaths(Carousel carousel) {
        dao.updatePath(carousel);
    }

    @Override
    public void deleteById(String carouselId) {
        dao.deleteCarousel(carouselId);
    }

    @Override
    public void updateById(Carousel carousel) {
        dao.updateCarousel(carousel);
    }

    @Override
    public void deleteByIds(String[] carouselIds) {
        dao.deleteCarousels(carouselIds);
    }
}
