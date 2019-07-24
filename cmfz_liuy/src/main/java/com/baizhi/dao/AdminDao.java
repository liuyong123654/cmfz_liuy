package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员
 */
public interface AdminDao {
    //查询所有管理员
    List<Admin> selectAll();
    Admin selectUsername(String username);
}
