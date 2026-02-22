package com.plataforma_educativa.administrador_usuarios.service;

import com.plataforma_educativa.administrador_usuarios.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);
    Estudiante save(Estudiante estudiante);
    void deleteById(Long id);
    Estudiante update(Estudiante estudiante);
}
