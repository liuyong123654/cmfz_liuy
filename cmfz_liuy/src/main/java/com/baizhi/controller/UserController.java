package com.baizhi.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.UserDao;
import com.baizhi.dto.UserDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserDao dao;
    @Autowired
    private UserService service;
    @RequestMapping("query")
    public UserDto query(Integer page,Integer rows){
        UserDto dto = service.queryByPage(page, rows);

        return dto;
    }
    @RequestMapping("edit")
    public String edit(User user,String oper,String[] id){
        if ("add".equals(oper)){
            String s = service.insertByUser(user);
            return s;
        }else if ("del".equals(oper)){
            service.deleteId(id[0]);
        }else {
            user.setUserId(id[0]);

            user.setProfile(null);
            service.updateByUser(user);
            return user.getUserId();
        }
        if (id.length>0){
            for (String s : id) {
                service.deleteId(s);
            }
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(MultipartFile profile, HttpServletRequest request, String userId, HttpServletResponse response){
        String originalFilename = profile.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("upload");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            profile.transferTo(new File(path,originalFilename));
            User user = new User();
            user.setUserId(userId);
            user.setProfile(originalFilename);
            service.updateId(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("updatestatus")
    public String updateStatus(String userId,String status){
        if (status.equals("正常")){
            service.updatestatus(userId,"冻结");
            return "1";
        }else {
            service.updatestatus(userId,"正常");
            return "1";
        }
    }
    @RequestMapping("derive")
    public void derive()throws Exception{
        List<User> list = dao.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","用户表"),User.class,list);
        workbook.write(new FileOutputStream("C:/testPOI/user.xls"));
    }
    @RequestMapping("abcd")
    public void addUser(MultipartFile file1) throws IOException, ParseException {
       Workbook workbook= new HSSFWorkbook(file1.getInputStream());//获取路经
        Sheet sheetAt = workbook.getSheetAt(0);//获取文件夹中第一个表
        int rowNum = sheetAt.getLastRowNum();//获取一个多少行
        List<User> list = new ArrayList<>();//创建结合接受多个对象
        for (int i = 1; i <rowNum; i++) {//遍历行数
             User user = new User();
            Row row = sheetAt.getRow(i + 1);
            Cell cell = row.getCell(0);
            user.setUserId(cell.getStringCellValue());
            Cell cell1 = row.getCell(1);
            user.setPhone(cell1.getStringCellValue());
            Cell cell2 = row.getCell(2);
            user.setPassword(cell2.getStringCellValue());
            Cell cell3 = row.getCell(3);
            user.setSalt(cell3.getStringCellValue());
            Cell cell4 = row.getCell(4);
            user.setDharmaName(cell4.getStringCellValue());
            Cell cell5 = row.getCell(5);
            user.setProvince(cell5.getStringCellValue());
            Cell cell6 = row.getCell(6);
            user.setCity(cell6.getStringCellValue());
            Cell cell7 = row.getCell(7);
            user.setGender(cell7.getStringCellValue());
            Cell cell8 = row.getCell(8);
            user.setPersonalSign(cell8.getStringCellValue());
            Cell cell9 = row.getCell(9);
            user.setProfile(cell9.getStringCellValue());
            Cell cell10 = row.getCell(10);
            user.setStatus(cell10.getStringCellValue());
            Cell cell11 = row.getCell(11);
            String value = cell11.getStringCellValue();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(value);
            user.setRegistTime(date);
            list.add(user);
        }
        for (User user : list) {
            service.insertByUser(user);
            System.out.println(user);
        }
    }
    @RequestMapping("derives")
    public void derives(HttpServletResponse response)throws Exception{
        response.reset();
        response.setContentType("application/x-msdownload");//设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        List<User> users = dao.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","用户表"),
                User.class, users);
        File saveFile = new File("excel");
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("C:/testPOI/user.xls");
        workbook.write(fos);
        fos.close();
    }
    @RequestMapping("goeasy")
    public void goeasy(){
        //GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io", "BC-194a1ecb03b1493fb120f0f2ed3cb85b");
    }


}
