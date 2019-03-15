package com.gdz.dynamic.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: guandezhi
 * @Date: 2019/3/14 21:06
 */
@Configuration
@MapperScan(basePackages = {"com.gdz.dynamic.mapper.china"}, sqlSessionTemplateRef = "chinaSqlSessionTemplate")
public class ChinaDataSourceConfig {

    @Bean(name = "chinaDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.china")
    public DataSource chinaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "chinaSqlSessionFactory")
    @Primary
    public SqlSessionFactory chinaSqlSessionFactory(@Qualifier("chinaDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/china/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "chinaTransactionManager")
    @Primary
    public DataSourceTransactionManager chinaTransactionManager(@Qualifier("chinaDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "chinaSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate chinaSqlSessionTemplate(@Qualifier("chinaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
