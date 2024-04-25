package org.example.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {

    // Expiration time in 10 minutes
    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "user_id",
            foreignKey = @ForeignKey(name = "FK_password_token_user")
    )
    private User user;

    public PasswordResetToken(User user, String token) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    public PasswordResetToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    private Date calculateExpirationTime(int expirationTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime()); // Current time
        calendar.add(Calendar.MINUTE, expirationTimeInMinutes); // Expiration time
        return new Date(calendar.getTime().getTime());
    }

    public boolean isExpired() {
        return new Date().after(this.expirationTime);
    }
}
