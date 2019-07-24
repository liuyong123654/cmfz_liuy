package com.baizhi.dto;

import com.baizhi.entity.Carousel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO主用来前后台传输数据
 * page:当前页
 * total:总页数
 * records:总页数
 * rows:该页的数据行
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuPageDto implements Serializable {
    private Integer page;
    private Integer total;
    private Integer records;
    private List<Carousel> rows;


}
