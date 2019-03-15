package com.gdz.dynamic.mapper.user;

/**
 * @Author: guandezhi
 * @Date: 2019/3/3 10:46
 */
public interface UserMapper {

       String queryUserName(Integer id);

       int addUser(String name);

}
