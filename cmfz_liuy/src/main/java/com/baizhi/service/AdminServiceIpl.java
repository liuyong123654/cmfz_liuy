package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceIpl implements AdminService {
    @Autowired
    private AdminDao dao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Admin> queryAll() {
        List<Admin> list = dao.selectAll();
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryUsername(Admin admin) {
        Map<String , Object> map = new HashMap<>();
        Admin selectedAdmin = dao.selectUsername(admin.getUsername());
        if (selectedAdmin == null){
            map.put("code",300);
            map.put("message","用户名不存在");
        }else if (selectedAdmin.getPassword().equals(admin.getPassword())){
            map.put("code",200);
            map.put("message","登陆成功");
        }else{
            map.put("code",400);
            map.put("message","密码错误");
        }
        return map;
    }


}
