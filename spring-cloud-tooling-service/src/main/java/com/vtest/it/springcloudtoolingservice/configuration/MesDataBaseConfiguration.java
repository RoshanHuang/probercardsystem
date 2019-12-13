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
@MapperScan(value = "com.vtest.it.springcloudtoolingservice.dao.testerLocation",sqlSessionTemplateRef = "mesSqlSessionTemplate")
public class MesDataBaseConfiguration {
    @Bean(name = "MesDataSouce",initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mes")
    public DruidDataSource getDataSource(){
        DruidDataSource dataSource= new DruidDataSource();
        try {
            dataSource.setFilters("stat,log4j,wall");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("mesSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Autowired @Qualifier("MesDataSouce") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/config.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/TesterLocation.xml"));
        return sessionFactoryBean.getObject();
    }
    @Bean("mesSqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Autowired @Qualifier("mesSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory);
        return  sqlSessionTemplate;
    }
    @Bean("mesTransactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Autowired @Qualifier("MesDataSouce") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }
}
