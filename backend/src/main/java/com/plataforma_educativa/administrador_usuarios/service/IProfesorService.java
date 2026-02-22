package com.plataforma_educativa.administrador_usuarios.service;

import com.plataforma_educativa.administrador_usuarios.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface IProfesorService {
    List<Profesor> findAll();
    Optional<Profesor> findById(Long id);
    Profesor save(Profesor profe);
    void deleteById(Long id);
    Profesor update(Profesor profe);
}
