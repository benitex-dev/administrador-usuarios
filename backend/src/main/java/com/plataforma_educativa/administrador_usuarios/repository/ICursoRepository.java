package com.plataforma_educativa.administrador_usuarios.repository;

import com.plataforma_educativa.administrador_usuarios.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {
    Optional<Curso> findCursoEntityByNombreCurso(String nombreCurso);

}
