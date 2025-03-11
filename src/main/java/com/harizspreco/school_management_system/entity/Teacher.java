package com.harizspreco.school_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

// Entitet za Nastavnika
@Entity
@Data
@ToString(exclude = "classroom")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "teacher", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Classroom classroom;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}