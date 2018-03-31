package com.shuframework.admin.model;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统管理_用户
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
@TableName("sys_user")
@ApiModel(description="用户对象user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 名称
     */
	 @ApiModelProperty(value="名称",name="name", example="aa")
	private String name;
    /**
     * 登录名
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 密码
     */
	private String pwd;
    /**
     * 性别  1男 2女
     */
	private String sex;
    /**
     * 图标
     */
	private String icon;
    /**
     * 手机号
     */
	@TableField("phone_num")
	private String phoneNum;
    /**
     * 备用号
     */
	@TableField("phone_num2")
	private String phoneNum2;
    /**
     * 地址
     */
	private String address;
    /**
     * 用户状态
     */
	private String state;
    /**
     * 部门id
     */
	private String deptid;
    /**
     * 入职时间
     */
	private Date createtime;
    /**
     * 离职时间
     */
	private Date leavetime;
    /**
     * 用户类型
     */
	@TableField("user_type")
	private String userType;
    /**
     * 排序号
     */
	private Integer sortid;
    /**
     * 更新时间
     */
	private Date updatetime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum2() {
		return phoneNum2;
	}

	public void setPhoneNum2(String phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getSortid() {
		return sortid;
	}

	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysUser{" +
			"id=" + id +
			", name=" + name +
			", loginName=" + loginName +
			", pwd=" + pwd +
			", sex=" + sex +
			", icon=" + icon +
			", phoneNum=" + phoneNum +
			", phoneNum2=" + phoneNum2 +
			", address=" + address +
			", state=" + state +
			", deptid=" + deptid +
			", createtime=" + createtime +
			", leavetime=" + leavetime +
			", userType=" + userType +
			", sortid=" + sortid +
			", updatetime=" + updatetime +
			"}";
	}
}
