package com.example.carrental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name must not be empty")
    @Size(max = 255, message = "First name must not exceed 255 characters")
    @Column(name = "FIRST_NAME")
    private String first_name;

    @NotEmpty(message = "Last name must not be empty")
    @Size(max = 255, message = "Last name must not exceed 255 characters")
    @Column(name = "LAST_NAME")
    private String last_name;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotEmpty(message = "Address must not be empty")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    @Column(name = "ADDRESS")
    private String address;

    @NotEmpty(message = "Phone number must not be empty")
    @Pattern(regexp = "^\\d{5,15}$", message = "Phone number must be between 5 and 15 digits")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern.List({
            @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter"),
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")
    })
    @Column(name = "PASSWORD")
    private String password;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, underscores, periods, or dashes")
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Min(value = 18, message = "Age must be at least 18")
    @Column(name = "AGE")
    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
