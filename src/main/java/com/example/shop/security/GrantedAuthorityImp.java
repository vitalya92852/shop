package com.example.shop.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImp implements GrantedAuthority {

    private final String authority;

    private final boolean role;

    public GrantedAuthorityImp(String authority,boolean role){
        this.authority = authority;
        this.role = role;
    }
    @Override
    public String getAuthority() {
        if(role){
            return "ROLE_"+authority;
        }
        return authority;
    }
}
