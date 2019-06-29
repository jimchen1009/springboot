package com.ximuyi.demo.mybatis.mapper;

import com.ximuyi.demo.mybatis.model.MybatisUser;
import com.ximuyi.demo.mybatis.model.MybatisUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MybatisUserMapper {

    List<MybatisUser> selectAllUser();

    int deleteByExample(MybatisUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(MybatisUser record);

    int insertSelective(MybatisUser record);

    List<MybatisUser> selectByExample(MybatisUserExample example);

    MybatisUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") MybatisUser record, @Param("example") MybatisUserExample example);

    int updateByExample(@Param("record") MybatisUser record, @Param("example") MybatisUserExample example);

    int updateByPrimaryKeySelective(MybatisUser record);

    int updateByPrimaryKey(MybatisUser record);
}