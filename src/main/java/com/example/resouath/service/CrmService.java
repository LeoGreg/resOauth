package com.example.resouath.service;

import com.example.sys.model.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public interface CrmService {

    Employee getByUsername(String ssn);

}
