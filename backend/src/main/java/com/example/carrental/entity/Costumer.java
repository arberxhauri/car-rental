package com.example.carrental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "COSTUMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 255, message = "First name cannot exceed 255 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 255, message = "Last name cannot exceed 255 characters")
    private String lastName;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @Email(message = "Not a valid email")
    private String email;

    private String password;

    private String address;

    @Pattern(regexp = "^[0-9]*$", message = "Phone number must contain only digits")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

//    @OneToMany( mappedBy = "costumer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Reservation> reservationSet = new HashSet<>();
//
//    @OneToMany( mappedBy = "costumer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Review> reviewSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}