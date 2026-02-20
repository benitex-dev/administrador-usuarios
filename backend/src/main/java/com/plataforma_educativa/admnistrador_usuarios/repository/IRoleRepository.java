package com.plataforma_educativa.admnistrador_usuarios.repository;

import com.plataforma_educativa.admnistrador_usuarios.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findRoleEntityByRole(String rolename);
}
