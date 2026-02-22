package com.plataforma_educativa.administrador_usuarios.controller;

import com.plataforma_educativa.administrador_usuarios.model.Profesor;
import com.plataforma_educativa.administrador_usuarios.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    @Autowired
    private IProfesorService profeService;

    @GetMapping
    public ResponseEntity<List<Profesor>> getAll() {
        List<Profesor> profes = profeService.findAll();
        return ResponseEntity.ok(profes);
    }
    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Profesor> getById(@PathVariable Long id){
        Optional<Profesor> estudiante = profeService.findById(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .notFound()
                        .build());
    }


    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMIN')")
    public ResponseEntity<Profesor> create(@RequestBody Profesor profe){
        Profesor newProfe = profeService.save(profe);
        return ResponseEntity.ok(newProfe);
    }


}
