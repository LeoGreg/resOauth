package com.example.resouath.config;


import com.example.resouath.service.CrmService;
import com.example.sys.model.Authority;
import com.example.sys.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private CrmService crmService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User oauthUser = (User) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();


        Employee employee = crmService.getByUsername(oauthUser.getUsername());

        if (employee != null) {
            additionalInfo.put("id", employee.getId());
            additionalInfo.put("ssn", employee.getSsn());
            additionalInfo.put("first_name", employee.getFirst_name());
            additionalInfo.put("last_name", employee.getLast_name());
            additionalInfo.put("type", employee.getType());
            additionalInfo.put("mail", employee.getMail());
            additionalInfo.put("wage", employee.getWage());


            HashMap<Integer, String> roles = new HashMap<>();
            for (Authority authority : employee.getAuthorities()) {
                roles.put(authority.getId(), authority.getName());
            }
            additionalInfo.put("roles", roles);

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }


        return accessToken;
    }

}