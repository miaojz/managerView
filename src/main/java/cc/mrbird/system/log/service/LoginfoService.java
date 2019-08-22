package cc.mrbird.system.log.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cc.mrbird.system.log.model.Loginfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yux
 * @since 2019-07-16
 */
public interface LoginfoService extends IService<Loginfo> {
	
	 public void summary();
	 
	 Page<Map<String,Object>> selectListPage(int current, int number);
}
