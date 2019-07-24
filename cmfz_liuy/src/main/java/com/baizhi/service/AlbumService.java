package com.baizhi.service;

import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;

public interface AlbumService {

    //分页查询
    public AlbumDto queryAllPage(Integer page,Integer rows);
    //添加专辑
    public String insertByAlbum(Album album);
    //删除专辑
    public void deleteId(String albumId);
    //修改专辑
    public void updateId(Album album);
    //修改图片路径
    public void updateBycover(Album album);
}
