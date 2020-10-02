package com.example.resouath.service;

import com.example.sys.model.Employee;
import com.example.sys.model.EmployeeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CrmService crmService;


    @Override
    public UserDetails loadUserByUsername(String ssn) throws UsernameNotFoundException {


        Employee employee = crmService.getByUsername(ssn);
        if (employee == null) {
            throw new UsernameNotFoundException("user not found");
        }

        if (employee.getStatus() != EmployeeStatus.ON) {
            throw new LockedException("User is unverified");
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = employee.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(employee.getSsn(), employee.getPassword(), simpleGrantedAuthorities);
    }
}
