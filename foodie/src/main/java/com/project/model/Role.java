package com.project.model;

import javax.validation.constraints.Pattern;

//@Pattern(regexp="(CUSTOMER|VENDOR|ADMIN)",message="{UserValidator.INVALID_ROLE_TYPE}")
public enum Role {
	ADMIN,CUSTOMER,VENDOR
}
