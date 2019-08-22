package cc.mrbird.system.log.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Test {
	
	//： 表示 每隔 5000 毫秒执行一次
	//@Scheduled(fixedRate = 5000) 
	public void reportCurrentTime() {
	    System.out.println("每隔五秒钟执行一次： " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
