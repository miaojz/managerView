package cc.mrbird.common.config;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import cc.mrbird.common.datasource.DataSourceEnum;
import cc.mrbird.common.datasource.MultipleDataSource;

@EnableTransactionManagement
@Configuration
@MapperScan("cc.mrbird.system.*.dao")//输入你的dao层的包
public class MybatisPlusConfig {

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * SQL执行效率插件
	 */
	@Bean
	@Profile({"dev","qa"})// 设置 dev test 环境开启
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setMaxTime(1000);
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}

	@Bean(name = "db1")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db1" )
	public DataSource db1() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "db2")
	@ConfigurationProperties(prefix = "spring.datasource.druid.db2" )
	public DataSource db2() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源配置
	 * @return
	 */
	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
		MultipleDataSource multipleDataSource = new MultipleDataSource();
		Map< Object, Object > targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceEnum.DB1.getValue(), db1);
		targetDataSources.put(DataSourceEnum.DB2.getValue(), db2);
		//添加数据源
		multipleDataSource.setTargetDataSources(targetDataSources);
		//设置默认数据源
		multipleDataSource.setDefaultTargetDataSource(db1);
		return multipleDataSource;
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(multipleDataSource(db1(),db2()));
		//sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));

		MybatisConfiguration configuration = new MybatisConfiguration();
		//configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
				paginationInterceptor() //添加分页功能
		});
		//sqlSessionFactory.setGlobalConfig(globalConfiguration());
		return sqlSessionFactory.getObject();
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(MultipleDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}


	/*@Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
        //conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }*/
}