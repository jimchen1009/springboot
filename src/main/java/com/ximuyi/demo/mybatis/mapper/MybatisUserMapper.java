package com.ximuyi.demo.mybatis.mapper;

import com.ximuyi.demo.mybatis.model.MybatisUser;
import com.ximuyi.demo.mybatis.model.MybatisUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MybatisUserMapper {
    int deleteByExample(MybatisUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(MybatisUser row);

    int insertSelective(MybatisUser row);

    List<MybatisUser> selectByExample(MybatisUserExample example);

    MybatisUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("row") MybatisUser row, @Param("example") MybatisUserExample example);

    int updateByExample(@Param("row") MybatisUser row, @Param("example") MybatisUserExample example);

    int updateByPrimaryKeySelective(MybatisUser row);

    int updateByPrimaryKey(MybatisUser row);
}