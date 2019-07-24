package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbummDao {


    //总行数
    public Integer selectCount();
    //分页查询
    public List<Album> selectPage(@Param("Page") Integer Page,@Param("Size") Integer Size);
    //添加专辑
    public void insertAlbum(Album album);
    //根据id删除专辑
    public void deleteById(String albumId);
    //根据id修改专辑
    public void updateById(Album album);
    public void updateByCover(Album album);
    //根据id查询数量
    public int selectById(String albumId);
}
