package com.plataforma_educativa.administrador_usuarios.repository;

import com.plataforma_educativa.administrador_usuarios.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission,Long> {
}
