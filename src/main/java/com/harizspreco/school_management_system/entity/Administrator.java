package com.harizspreco.school_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Entitet za Administratora
@Entity
@Data
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}