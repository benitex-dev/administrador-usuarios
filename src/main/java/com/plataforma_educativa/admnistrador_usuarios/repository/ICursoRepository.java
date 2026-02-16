package com.plataforma_educativa.admnistrador_usuarios.repository;

import com.plataforma_educativa.admnistrador_usuarios.model.Curso;
import com.plataforma_educativa.admnistrador_usuarios.model.Permission;
import com.plataforma_educativa.admnistrador_usuarios.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICursoRepository extends JpaRepository<Curso,Long> {
    Optional<Curso> findCursoEntityByNombreCurso(String nombreCurso);

}
