package cc.mrbird.system.log.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.mrbird.system.log.dao.LoginfoMapper;
import cc.mrbird.system.log.model.Loginfo;
import cc.mrbird.system.log.service.LoginfoService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yux
 * @since 2019-07-16
 */
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {
	@Autowired
	public LoginfoMapper loginfoMapper;

	public Page<Map<String, Object>> selectListPage(int current, int number) {
        // 新建分页
        Page<Map<String,Object>> page = new Page<Map<String,Object>>(current, number);
         
        // 返回分页结果 1为id
        return page.setRecords(this.loginfoMapper.dyGetUserList(page,1));
	}

	@Transactional
	public void summary() {
		Loginfo loginfo=new Loginfo();
		loginfo.setLogId(3);
		save(loginfo);
		save(loginfo);
	}

}
