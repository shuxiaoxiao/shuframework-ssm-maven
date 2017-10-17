package com.shuframework.admin.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 系统管理_操作日志表
 * </p>
 *
 * @author shuheng
 * @since 2017-10-16
 */
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 登录名
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 角色名
     */
	@TableField("role_name")
	private String roleName;
    /**
     * 内容
     */
	private String content;
    /**
     * 客户端ip
     */
	@TableField("client_ip")
	private String clientIp;
    /**
     * 创建时间
     */
	private Date createtime;
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
		return "SysLog{" +
			"id=" + id +
			", loginName=" + loginName +
			", roleName=" + roleName +
			", content=" + content +
			", clientIp=" + clientIp +
			", createtime=" + createtime +
			", updatetime=" + updatetime +
			"}";
	}
}
