package com.plataforma_educativa.admnistrador_usuarios.repository;

import com.plataforma_educativa.admnistrador_usuarios.model.Permission;
import com.plataforma_educativa.admnistrador_usuarios.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor,Long> {
}
