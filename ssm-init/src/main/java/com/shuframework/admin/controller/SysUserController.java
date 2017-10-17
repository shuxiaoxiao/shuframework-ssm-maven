package com.shuframework.admin.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.shuframework.admin.model.SysUser;
import com.shuframework.admin.service.SysUserService;
import com.shuframework.admin.vo.UserVo;
import com.shuframework.result.Result;
import com.shuframework.util.DigestUtil;

/**
 * <p>
 * 系统管理_用户 前端控制器
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/add")
    @ResponseBody
    public Result add(UserVo userVo) {
//        List<User> list = userService.selectByLoginName(userVo);
//        if (list != null && !list.isEmpty()) {
//            return renderError("登录名已存在!");
//        }
//        String salt = StringUtils.getUUId();
//        String pwd = passwordHash.toHex(userVo.getPassword(), salt);
//        userVo.setSalt(salt);
//        userVo.setPassword(pwd);
//        userService.insertByVo(userVo);
//        return renderSuccess("添加成功");
		Result result = new Result();
		
		String loginName = "测试aa";
		String password = "test";
		String pwd = DigestUtil.md5(password);//加密后
		SysUser user = new SysUser();
		user.setCreatetime(new Date());
		user.setLoginName(loginName);
		user.setPwd(pwd);
		
		userService.insert(user);
		
		result.setSuccess(true);
		result.setCode(20000);
		result.setData(user);
		return result;
    }
	
	
	@RequestMapping("/list")
    @ResponseBody
    public Result list(int pageIndex, int pageSize) {
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
