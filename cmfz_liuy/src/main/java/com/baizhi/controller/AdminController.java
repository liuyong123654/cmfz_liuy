package com.baizhi.controller;


import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(Admin admin,HttpSession session,HttpServletResponse response){

        Map<String, Object> login = service.queryUsername(admin);
        session.setAttribute("admin",admin);
        return login;
    }
    @RequestMapping("createImg")
    public void createImg(HttpServletResponse response, HttpSession session) throws IOException {
        String code = SecurityCode.getSecurityCode();
        session.setAttribute("code1",code);
        BufferedImage image = SecurityImage.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpeg",out);
    }
}
