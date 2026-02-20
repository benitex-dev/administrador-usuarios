package com.plataforma_educativa.admnistrador_usuarios.controller;

import com.plataforma_educativa.admnistrador_usuarios.model.Curso;
import com.plataforma_educativa.admnistrador_usuarios.model.Estudiante;
import com.plataforma_educativa.admnistrador_usuarios.model.Permission;
import com.plataforma_educativa.admnistrador_usuarios.model.Profesor;
import com.plataforma_educativa.admnistrador_usuarios.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAll() {
        List<Estudiante> estudiantes = estudianteService.findAll();
        return ResponseEntity.ok(estudiantes);
    }
    @GetMapping("/{id}")
   // @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Estudiante> getById(@PathVariable Long id){
        Optional<Estudiante> estudiante = estudianteService.findById(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .notFound()
                        .build());
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMIN')")
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante){
        Estudiante newEstudiante = estudianteService.save(estudiante);
        return ResponseEntity.ok(newEstudiante);
    }
}
