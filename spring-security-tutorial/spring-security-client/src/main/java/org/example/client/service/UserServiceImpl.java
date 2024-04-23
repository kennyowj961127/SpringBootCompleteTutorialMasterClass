package org.example.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.entity.User;
import org.example.client.entity.VerificationToken;
import org.example.client.model.UserModel;
import org.example.client.repository.UserRepository;
import org.example.client.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private VerificationTokenRepository verificationTokenRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        // Save the token in the database
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null) {
            return "invalid";
        } else {
            if(verificationToken.isExpired()) {
                verificationTokenRepository.delete(verificationToken);
                return "expired";
            } else {
                User user = verificationToken.getUser();
                user.setEnabled(true);
                userRepository.save(user);
                return "valid";
            }
        }

    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        if(verificationToken == null) {
            return null;
        }
        verificationToken.setToken(UUID.randomUUID().toString());
        log.info("New token: {}", verificationToken);
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
}
