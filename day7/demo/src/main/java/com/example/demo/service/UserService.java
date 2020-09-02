package com.example.demo.service;

import com.example.demo.Dom.Response;
import com.example.demo.Dom.User;

public interface UserService {
    Response register(User user);
    Response login(User user);
}
