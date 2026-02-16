package com.plataforma_educativa.admnistrador_usuarios.controller;

import com.plataforma_educativa.admnistrador_usuarios.model.*;
import com.plataforma_educativa.admnistrador_usuarios.service.ICursoService;
import com.plataforma_educativa.admnistrador_usuarios.service.IEstudianteService;
import com.plataforma_educativa.admnistrador_usuarios.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        Set<Estudiante> estudiantes = new HashSet<>();
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
        newCurso.setNombreCurso(curso.getNombreCurso());
        cursoService.save(newCurso);

        return ResponseEntity.ok(newCurso);
    }
    @PatchMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity edit(@RequestBody Curso curso){

        //1. primero buscamos el curso por su nombre
        Optional<Curso> cursoEdit = cursoService.findCursoEntityByNombreCurso(curso.getNombreCurso());

        //2.validamos que hayamos encontrado el curso
        if(cursoEdit.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        //3.buscamos los estudiantes que tiene el curso, para editarlos y agregarle más
        Set<Estudiante> estudiantesList = new HashSet<>();
        Estudiante readEstudiante;

        // Recuperar estudiante/s por su ID
        for (Estudiante est : curso.getEstudiantes()) {
            readEstudiante = estudianteService.findById(est.getIdEstudiante()).orElse(null);
            if (readEstudiante != null) {
                //si encuentro, guardo en la lista
                estudiantesList.add(readEstudiante);
            }
        }

        // Recuperar profesor por su ID
        Profesor readProf=profesorService.findById(curso.getProfe().getIdProfesor()).orElse(null);

        //cargamos los datos en nuestra instancia del curso editado
        cursoEdit.get().setEstudiantes(estudiantesList);
        cursoEdit.get().setProfe(readProf);

        Curso newCurso = cursoService.save(cursoEdit.get());
        return ResponseEntity.ok(newCurso);
    }

}
