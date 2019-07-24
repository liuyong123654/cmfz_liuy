package com.baizhi.service;

import com.baizhi.dao.CarouselDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.dto.AlbumDto;
import com.baizhi.dto.ChapterDto;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Transactional
@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao dao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public ChapterDto queryByPage(Integer page, Integer rows,String albumId) {
        ChapterDto dto = new ChapterDto();
        dto.setPage(page);
        Integer count = dao.selectCount();
        Integer i = count%rows == 0 ? count/rows : count/rows+1;
        dto.setTotal(i);//总页数
        dto.setRecords(count);//总行数
        dto.setRows(dao.selectPage(page,rows,albumId));
        return dto;
    }

    @Override
    public String insertById(Chapter chapter) {

        String s = UUID.randomUUID().toString();
        chapter.setChapterId(s);
        dao.insertChapter(chapter);
        return s;
    }

    @Override
    public void deleteById(String chapterId) {
        dao.deleteById(chapterId);
    }

    @Override
    public void updateById(Chapter chapter) {
        dao.updateById(chapter);
    }

    @Override
    public void modifyDownpath(Chapter chapter) {
        dao.updateDownpath(chapter);
    }
}
