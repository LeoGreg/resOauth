package com.example.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Employee implements Serializable {

    private int id;

    private String ssn;//username

    private String password;

    private String type;

    private String first_name;

    private String last_name;

    private String mail;

    private EmployeeStatus status;

    private double wage;

    private List<Authority> authorities;


}
