package org.example.client.service;


import org.example.client.entity.User;
import org.example.client.entity.VerificationToken;
import org.example.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
