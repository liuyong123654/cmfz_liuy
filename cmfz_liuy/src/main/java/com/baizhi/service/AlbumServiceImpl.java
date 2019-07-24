package com.baizhi.service;


import com.baizhi.dao.AlbummDao;
import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbummDao dao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public AlbumDto queryAllPage(Integer page, Integer rows) {
        AlbumDto dto = new AlbumDto();//创建dto对象
        dto.setPage(page);//当前页
        Integer count = dao.selectCount();
        Integer i = count%rows == 0 ? count/rows : count/rows+1;
        dto.setTotal(i);//总页数
        System.out.println(i);
        dto.setRecords(count);//总行数
        dto.setRows(dao.selectPage(page,rows));
        return dto;
    }

    @Override
    public String insertByAlbum(Album album) {
        String s = UUID.randomUUID().toString();
        album.setAlbumId(s);
        dao.insertAlbum(album);
        return s;
    }

    @Override
    public void deleteId(String albumId) {
            dao.deleteById(albumId);
    }

    @Override
    public void updateId(Album album) {
        dao.updateById(album);
    }

    @Override
    public void updateBycover(Album album) {
        dao.updateByCover(album);
    }
}
