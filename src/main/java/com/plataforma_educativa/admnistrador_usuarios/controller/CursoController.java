package com.plataforma_educativa.admnistrador_usuarios.controller;

import com.plataforma_educativa.admnistrador_usuarios.model.Curso;
import com.plataforma_educativa.admnistrador_usuarios.model.Estudiante;
import com.plataforma_educativa.admnistrador_usuarios.model.Profesor;
import com.plataforma_educativa.admnistrador_usuarios.model.UserSec;
import com.plataforma_educativa.admnistrador_usuarios.service.ICursoService;
import com.plataforma_educativa.admnistrador_usuarios.service.IEstudianteService;
import com.plataforma_educativa.admnistrador_usuarios.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private ICursoService cursoService;
    @Autowired
    private IProfesorService profesorService;
    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> cursos = cursoService.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Curso> getById(@PathVariable Long id){
        Optional<Curso> curso = cursoService.findById(id);
        return curso.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .notFound()
                        .build());
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMIN')")
    public ResponseEntity<Curso> create(@RequestBody Curso curso){

        //obtenemos los alumnos
        List<Estudiante> estudiantes = new ArrayList<>();
        //buscamos los alumnos por id
        for ( Estudiante estudiante: curso.getEstudiantes()){
            estudiantes.add(estudianteService.findById(estudiante.getIdEstudiante()).orElse(null));
        }

        //obtenemos el profesor
        Profesor profe = new Profesor();
        profe = profesorService.findById(curso.getProfe().getIdProfesor()).orElse(null);

        //guardamos el nuevo curso con su profesor y sus estudiantes
        Curso newCurso = new Curso();
        newCurso.setProfe(profe);
        newCurso.setEstudiantes(estudiantes);

        cursoService.save(newCurso);

        return ResponseEntity.ok(newCurso);
    }

}
