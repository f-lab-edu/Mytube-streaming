//package com.flab.Mytube.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//@SpringBootConfiguration
////@PropertySource("classpath:/application.properties")
//@MapperScan(value = "com.flab.Mytube", sqlSessionFactoryRef = "sqlSessionFactory")
//public class DatabaseConfig {
////    @Value("${spring.datasource.mapper-location}")
////    String mPath;
//
////    @Bean
////    @ConfigurationProperties(prefix = "spring.datasource")
////    public DataSource dataSource(){
////        return DataSourceBuilder.create().build();
////    }
////
////    @Bean
////    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception{
////        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
////        sqlSessionFactoryBean.setDataSource(DataSource);
////        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResource(mPath));
////        return sqlSessionFactoryBean.getObject();
////    }
////
////    @Bean(name="SessionTemplate")
////    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory){
////        return new SqlSessionTemplate(firstSqlSessionFactory);
////    }
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.hikari")
//    public HikariConfig hikariConfig(){
//        return new HikariConfig();
//    }
//
//    SqlSessionTemplate sqlSessionTemplate;
//    public SqlSessionTemplate getSqlSessionTemplate(){
//        return sqlSessionTemplate;
//    }
//
//    @Bean
//    public DataSource dataSource() throws Exception{
//        DataSource dataSource = new HikariDataSource(hikariConfig());
//        System.out.println(dataSource.toString());
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
////        sqlSessionFactoryBean.setTypeAliasesPackage("board.board.dto");
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
//        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
