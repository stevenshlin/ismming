package com.ismming.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections4.CollectionUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class UserProfile implements Serializable {

    private static final long serialVersionUID = -4364901733996854868L;

    private String username;
    private String password;
    private List<UserRole> roles;
    private boolean enabled;
    private Date loginTime;

    public UserProfile() {
    }

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>(Arrays.asList(UserRole.USER));
        this.enabled = true;
        this.loginTime = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @JsonIgnore
    public String getFormattedRoles() {
        if (CollectionUtils.isNotEmpty(this.roles)) {
            return this.roles.stream().map(UserRole::getName).collect(Collectors.joining(","));
        }
        return null;
    }

}
