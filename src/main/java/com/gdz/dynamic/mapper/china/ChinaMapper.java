package com.gdz.dynamic.mapper.china;

/**
 * @Author: guandezhi
 * @Date: 2019/3/3 10:46
 */
public interface ChinaMapper {

       String queryCountryName(Integer id);


       int addCountry(String name);

}
