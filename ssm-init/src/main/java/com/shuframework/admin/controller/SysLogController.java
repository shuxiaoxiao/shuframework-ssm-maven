package com.shuframework.admin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.shuframework.admin.model.SysUser;
import com.shuframework.admin.service.SysUserService;
import com.shuframework.result.Result;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 系统管理_操作日志表 前端控制器
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController {
	@Autowired
	private SysUserService userService;
	
	@GetMapping("/list")
//    @ResponseBody
	@ApiOperation(value = "分页查询", notes = "aa", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
	//该种方式默认是paramType = "body"，application/json，一般不存在多个，不同paramType可以存在一个
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true, paramType = "query", dataType = "integer"),
        @ApiImplicitParam(name = "pageSize", value = "条数", required = true, paramType = "query", dataType = "integer")
	})
	public Result list(Integer pageIndex, Integer pageSize) {
		Result result = new Result();
		
		//第一页默认为 1
		Page<SysUser> page = new Page<>(pageIndex, pageSize);
		page = userService.selectPage(page);
		
		result.setSuccess(true);
		result.setCode(20000);
		result.setData(page);
		return result;
	}
}
