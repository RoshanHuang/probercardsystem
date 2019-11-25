package com.vtest.it.springcloudauthandoperator.service.userService.impl;

import common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserInformationService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/user-service/auth/user/{1}", User.class, username);
        return new com.vtest.it.springcloudauthandoperator.domain.User(responseEntity.getBody());
    }

    public void registerUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user);
        restTemplate.postForObject("http://192.168.10.182:20300/user-service/auth/user/", entity, Integer.class);
    }

}
