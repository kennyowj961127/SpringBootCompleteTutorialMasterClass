package org.example.client.event;

import org.example.client.entity.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
    }
}
