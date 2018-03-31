package com.shuframework.admin.mapper;

import com.shuframework.admin.model.SysUser;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 系统管理_用户 Mapper 接口
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	
	List<SysUser> selectUserPage(Pagination page, Map<String, Object> params);
}