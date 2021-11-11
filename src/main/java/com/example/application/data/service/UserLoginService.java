package com.example.application.data.service;

import com.example.application.data.entity.User;

import com.example.application.data.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class UserLoginService extends CrudService<UserLogin, Integer> {

    private UserLoginRepository repository;

    public UserLoginService(@Autowired UserLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UserLoginRepository getRepository() {
        return repository;
    }

}
