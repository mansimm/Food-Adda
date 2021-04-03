package com.project.model;

import javax.validation.constraints.Pattern;

public class RolesDto {
	
	private Integer roleId;
	
	//@Pattern(regexp="(CUSTOMER|VENDOR)",message="{UserValidator.INVALID_ROLE_TYPE}")
	private Role roleType;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Role getRoleType() {
		return roleType;
	}
	public void setRoleType(Role role) {
		this.roleType = role;
	}
	

	
}