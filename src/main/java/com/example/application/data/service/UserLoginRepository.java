package com.example.application.data.service;

import com.example.application.data.entity.User;

import com.example.application.data.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import com.example.application.data.Role;
import javax.persistence.Lob;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

    UserLogin findByUsername(String username);
}
