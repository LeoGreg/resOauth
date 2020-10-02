package com.example.sys.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
public class Authority implements GrantedAuthority, Serializable {


    private int id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}