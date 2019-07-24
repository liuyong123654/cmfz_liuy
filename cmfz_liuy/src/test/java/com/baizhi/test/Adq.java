package com.baizhi.test;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.App;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.CarouselService;
import com.baizhi.service.CarouselServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Adq {
    //@Autowired
    //private CarouselService service;
    @Autowired
    UserDao dao;
    @Test
    /*public void m2(){
        Carousel carousel = new Carousel();
        carousel.setCarouselId("438aaf74-2267-468d-abad-3a7687251f99");
        carousel.setImgPath("aaaa");
        service.updatePaths(carousel);
    }*/
    public void m1()throws IOException {
        List<User> list = dao.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","学生表"),//excel中的表题和表名
                User .class, list);//要导出的哪个实体类,参数二是数据库查出来的集合
        workbook.write(new FileOutputStream("C:/testPOI/第一个easyExcel文档.xls"));
        workbook.close();
    }
}
