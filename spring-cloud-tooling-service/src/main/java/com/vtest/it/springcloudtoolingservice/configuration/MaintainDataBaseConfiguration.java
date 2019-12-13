package com.vtest.it.springcloudtoolingservice.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(value = "com.vtest.it.springcloudtoolingservice.dao.config",sqlSessionTemplateRef = "maintainSqlSessionTemplate")
public class MaintainDataBaseConfiguration {
    @Bean(name = "MaintainDataSource",initMethod = "init",destroyMethod = "close")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.maintain")
    public DruidDataSource getDataSource(){
        DruidDataSource dataSource= new DruidDataSource();
        try {
            dataSource.setFilters("stat,log4j,wall");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dataSource;
    }

    @Primary
    @Bean("maintainSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Autowired @Qualifier("MaintainDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/config.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean("maintainSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Autowired @Qualifier("maintainSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory);
        return  sqlSessionTemplate;
    }

    @Primary
    @Bean("maintainTransactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Autowired @Qualifier("MaintainDataSource") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }
}
