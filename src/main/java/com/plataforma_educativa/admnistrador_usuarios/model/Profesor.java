package com.plataforma_educativa.admnistrador_usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profesores")

public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesor;
    private String nombre;
    private String apellido;
    @OneToMany(mappedBy = "profe")
    private Set<Curso> listCursos =  new HashSet<>();

}
