package com.project.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.project.model.Role;

public class RoleTypeSubSetValidator implements ConstraintValidator<RoleTypeSubset, Role> {
    private Role[] subset;

    @Override
    public void initialize(RoleTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}