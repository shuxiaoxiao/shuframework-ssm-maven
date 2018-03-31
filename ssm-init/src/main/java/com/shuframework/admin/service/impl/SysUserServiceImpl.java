package com.shuframework.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shuframework.admin.mapper.SysUserMapper;
import com.shuframework.admin.model.SysUser;
import com.shuframework.admin.service.SysUserService;
import com.shuframework.result.PageConvert;
import com.shuframework.result.PageInfo;

/**
 * <p>
 * 系统管理_用户 服务实现类
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Override
	public PageInfo selectUserPage(PageInfo pageInfo) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getPageIndex(), pageInfo.getPageSize());
        page.setOrderByField(pageInfo.getSort());
        page.setAsc(pageInfo.getOrder().equalsIgnoreCase(PageConvert.ORDERBY_ASC));
        List<SysUser> list = sysUserMapper.selectUserPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
        
		return pageInfo;
	}

	@Override
	public Page<SysUser> selectUserPage(Page<SysUser> page) {
		List<SysUser> list = sysUserMapper.selectUserPage(page, page.getCondition());
		page.setRecords(list);
		
		return page;
	}
}
