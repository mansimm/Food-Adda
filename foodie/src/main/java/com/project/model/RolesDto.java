package com.project.model;


import com.project.validator.RoleTypeSubset;

public class RolesDto {
	
	private Integer roleId;
	//Custom validator for enum Role
	@RoleTypeSubset(anyOf = {Role.CUSTOMER, Role.VENDOR})
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