package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.dto.UserDto;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;
    @Override
    public UserDto queryByPage(Integer page, Integer rows) {
        UserDto dto = new UserDto();
        dto.setPage(page);
        Integer count = dao.selectCount();
        Integer i = count%rows == 0 ? count/rows : count/rows+1;
        dto.setTotal(i);//总页数
        dto.setRecords(count);//总行数
        dto.setRows(dao.selectByPage(page,rows));
        return dto;
    }

    @Override
    public String insertByUser(User user) {
        String s = UUID.randomUUID().toString();
        user.setUserId(s);
        dao.insertUser(user);
        return s;
    }

    @Override
    public void deleteId(String userId) {
            dao.deleteById(userId);
    }

    @Override
    public void updateId(User user) {
        dao.updateById(user);
    }

    @Override
    public void updateByUser(User user) {
            dao.updateUser(user);
    }

    @Override
    public void updatestatus(String userId, String status) {
        dao.updateStatus(userId,status);
    }
}
