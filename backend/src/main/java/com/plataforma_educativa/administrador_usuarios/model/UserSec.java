package com.plataforma_educativa.administrador_usuarios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserSec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;

    //usamos set porque no permite repetidos
// List permite repetidos
    @ManyToMany(fetch = FetchType.EAGER) //el EAGER me va a cargar todos los roles
    @JoinTable
            (name               = "user_roles",
                    joinColumns        = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rolesList = new HashSet<>();
}
