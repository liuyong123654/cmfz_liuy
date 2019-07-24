package com.baizhi.service;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.User;

public interface UserService {

    //分页查询
    public UserDto queryByPage(Integer page,Integer rows);
    //添加用户
    public String insertByUser(User user);
    //根据id删除
    public void deleteId(String userId);
    //根据id修改图像
    public void updateId(User user);
    //修改用户
    public void updateByUser(User user);
    //修改状态
    public void updatestatus(String userId,String status);

}
