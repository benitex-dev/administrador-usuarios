package com.plataforma_educativa.admnistrador_usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cursos")

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nombreCurso;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(name = "cursos_estudiantes",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private List<Estudiante> estudiantes=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_profesor",referencedColumnName = "idProfesor")
    private Profesor profe;
}
