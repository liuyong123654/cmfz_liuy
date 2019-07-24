package com.baizhi.dto;


import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    public Integer page;
    private Integer total;
    private Integer records;
    private List<User> rows;
}
