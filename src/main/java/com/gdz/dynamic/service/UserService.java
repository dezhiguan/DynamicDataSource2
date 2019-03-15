package com.gdz.dynamic.service;


import com.gdz.dynamic.mapper.china.ChinaMapper;
import com.gdz.dynamic.mapper.user.UserMapper;
import com.gdz.dynamic.mapper.world.WorldMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: guandezhi
 * @Date: 2019/3/3 10:45
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChinaMapper chinaMapper;

    @Autowired
    private WorldMapper worldMapper;


    public String getUserName() {
        String userName = userMapper.queryUserName(1);
        log.info("username:{}", userName);
        return userName;
    }

    public String getChinaName() {
        String chinaName = chinaMapper.queryCountryName(0);
        log.info("chinaname:{}", chinaName);
        return chinaName;
    }

    public String getWorldName() {
        String worldName = worldMapper.queryWorldName(7);
        log.info("worldname:{}", worldName);
        return worldName;
    }

    @Transactional(value = "userTransactionManager")
    public String addUser(int a) {
        chinaMapper.addCountry("sanming");
        int b = 1 / a;
        userMapper.addUser("gaundezhi");
        return "success";
    }

}
