package com.cs.personer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql数据库配置
 *
 * @author Jack
 */
@Configuration
@MapperScan(basePackages = {"com.cs.personer.dao"},
        sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MysqlConfig {

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Primary
    public DataSource getDataSource() {
        return new DruidDataSource();
    }

    @Bean("mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.cs.personer.model");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*" +
                ".xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "mysqlDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
