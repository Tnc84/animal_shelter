package com.tnc.service.domain;

import static com.tnc.service.constant.Authority.*;

public enum RoleEnum {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_HR(HR_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_USER(SUPER_ADMIN_AUTHORITIES);

    private final String[] authorities;

    RoleEnum(String... authorities){
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
