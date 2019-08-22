package cc.mrbird.system.log.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cc.mrbird.system.log.model.Loginfo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yux
 * @since 2019-07-16
 */
public interface LoginfoMapper extends BaseMapper<Loginfo> {
	@Select("select * from jl_student a LEFT JOIN jl_class b on a.class_id=b.id")
    List<Map<String, Object>> dyGetUserList(Page<Map<String,Object>> page,Integer id);
}
