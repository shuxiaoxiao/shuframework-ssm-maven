package com.shuframework.admin.service;

import com.shuframework.admin.model.SysUser;
import com.shuframework.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统管理_用户 服务类
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
public interface SysUserService extends IService<SysUser> {
	
	/**
	 * 用自定义的分页对象
	 * @param PageInfo
	 * @return
	 */
	PageInfo selectUserPage(PageInfo pageInfo);
	
	/**
	 * 用mybatis-plus的分页对象,如果有条件注意给condition赋值
	 * @param page
	 * @return
	 */
	Page<SysUser> selectUserPage(Page<SysUser> page);
	
}
