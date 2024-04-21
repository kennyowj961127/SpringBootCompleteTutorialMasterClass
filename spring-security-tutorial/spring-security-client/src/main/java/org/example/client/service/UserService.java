package org.example.client.service;


import org.example.client.entity.User;
import org.example.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
