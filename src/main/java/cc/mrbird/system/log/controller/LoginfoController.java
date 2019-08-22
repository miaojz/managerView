package cc.mrbird.system.log.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cc.mrbird.common.domin.QueryRequest;
import cc.mrbird.common.domin.ResponseBo;
import cc.mrbird.common.log.Log;
import cc.mrbird.common.poi.ExcelUtil;
import cc.mrbird.common.service.RedisService;
import cc.mrbird.system.log.model.Loginfo;
import cc.mrbird.system.log.service.LoginfoService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yux
 * @since 2019-07-16
 */
@Controller
@RequestMapping("/loginfo")
public class LoginfoController {
	@Autowired
	public LoginfoService loginfoService;
	
	@Autowired 
	public RedisService redisService;
	
	@Log("日志导出")
	@RequestMapping("/list")
	public void list(HttpServletResponse respons,QueryRequest queryRequest){
		QueryWrapper<Loginfo> queryWrapper=new QueryWrapper<Loginfo>();
		Page<Loginfo> page=new Page<Loginfo>(2,5);
		//return ResponseBo.ok(loginfoService.page(page, queryWrapper).getRecords());
		
/*		Page<Map<String, Object>> page=loginfoService.selectListPage(2, 5);
		List<Map<String, Object>> loginfos=page.getRecords();
		System.out.println(page.getTotal());*/
		
        try {
            List<Loginfo> list = loginfoService.page(page, queryWrapper).getRecords();
            ExcelUtil<Loginfo> util = new ExcelUtil<Loginfo>(Loginfo.class);
			util.exportExcel(list,"经开",respons,0,1);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	@Log("日志删除")
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo delete(int id){
		try {
			loginfoService.removeById(id);
			double a=3/0;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseBo.ok("删除成功！");
	}
	
	@Log("日志添加")
	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo add(){
		try {
			Loginfo loginfo=new Loginfo();
			loginfo.setLogId(1);
			loginfoService.save(loginfo);
			return ResponseBo.ok("添加成功！");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseBo.error("添加失败！");
	}
	
	
	@RequestMapping("/add2")
	@ResponseBody
	public ResponseBo add2(){
        loginfoService.summary();
		return null;
	}
	
	@RequestMapping("/redis")
	@ResponseBody
	public ResponseBo redis(){
        redisService.set("name", "小明");
        System.out.println(redisService.get("name"));
		return null;
	}
}

