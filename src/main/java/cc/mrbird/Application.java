package cc.mrbird;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cc.mrbird.common.config.FebsProperties;

//核心注解
@SpringBootApplication
//定时任务
@EnableScheduling
//事务
@EnableTransactionManagement
//异步调用
@EnableAsync
//开启缓存
@EnableCaching
public class Application {
	
    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .run(args);
    }
} 
