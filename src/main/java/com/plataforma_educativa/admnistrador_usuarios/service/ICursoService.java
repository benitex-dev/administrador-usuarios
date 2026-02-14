package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Curso;
import com.plataforma_educativa.admnistrador_usuarios.model.Role;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    void deleteById(Long id);
    Curso update(Curso curso);
}
