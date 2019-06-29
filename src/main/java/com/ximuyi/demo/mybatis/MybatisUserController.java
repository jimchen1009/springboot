package com.ximuyi.demo.mybatis;

import com.ximuyi.demo.mybatis.model.MybatisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mybatis")
public class MybatisUserController {

    /***
     * Description:
     *
     * Field userService in com.ximuyi.demo.mybatis.MybatisUserController required a single bean, but 2 were found:
     * 	- mybatisUserServiceImpl: defined in file [D:\demo\springboot\target\classes\com\ximuyi\demo\mybatis\MybatisUserServiceImpl.class]
     * 	- mybatisUserService: defined in file [D:\demo\springboot\target\classes\com\ximuyi\demo\mybatis\MybatisUserService.class]
     *
     * 	MybatisUserService 命名问题：改成 mybatisUserService
     */
    @Qualifier("mybatisUserServiceImpl")
    @Autowired
    private MybatisUserService mybatisUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<MybatisUser> getAccounts(){
        return mybatisUserService.selectAllUser();
    }

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(MybatisUser user){
        return mybatisUserService.addUser(user);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return mybatisUserService.findAllUser(pageNum, pageSize);
    }
}
