package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //添加用户
    public void insertUser(User user);
    //修改用户
    public void updateUser(User user);
    //修改id图像
    public void updateById(User user);
    //根据id删除用户
    public void deleteById(String userId);
    //查询总行数
    public Integer selectCount();
    //分页查询
    public List<User> selectByPage(@Param("Page") Integer Page,@Param("Size") Integer Size);
    //修改状态
    public void updateStatus(String userId,String status);
    public List<User> selectAll();
}
