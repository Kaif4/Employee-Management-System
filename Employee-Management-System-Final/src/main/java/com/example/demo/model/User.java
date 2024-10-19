package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    public @Size(min = 6, message = "Password should be at least 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Password should be at least 6 characters") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username is required") String username) {
        this.username = username;
    }
}
