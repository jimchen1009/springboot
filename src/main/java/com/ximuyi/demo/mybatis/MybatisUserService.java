package com.ximuyi.demo.mybatis;

import com.ximuyi.demo.mybatis.model.MybatisUser;

import java.util.List;

public interface MybatisUserService {

    List<MybatisUser> selectAllUser();

    int addUser(MybatisUser user);

    List<MybatisUser> findAllUser(int pageNum, int pageSize);
}
