package com.yangmao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangmao.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 31067
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-11-08 16:08:43
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




