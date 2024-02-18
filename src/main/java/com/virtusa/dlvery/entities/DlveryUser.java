package com.virtusa.dlvery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dlvery_user_tbl")
public class DlveryUser {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userID;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Email(message = "Email invalid")
    @NotBlank(message = "Field can not be blank")
    @NotNull(message = "Field can not be null")
    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;


    @NotBlank(message = "Field can not be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;


    @NotBlank(message = "Field can not be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "OtherDetails are required")
    private String otherDetails;

    // Constructor without ID
    public DlveryUser(String username, String password, String otherDetails) {
        this.username = username;
        this.password = password;
        this.otherDetails = otherDetails;
    }

    // Getters and Setters

    // You might want to add Bcrypt hashing for the password setter
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

}
