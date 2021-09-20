package com.tnc.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDomain {
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String profileImageUrl;
    private String username;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private String[] roles;
    private String[] authorities;
    private boolean isActive;
    private boolean isNotActive;


}
