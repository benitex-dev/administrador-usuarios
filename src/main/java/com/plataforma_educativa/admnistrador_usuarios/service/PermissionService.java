package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Permission;
import com.plataforma_educativa.admnistrador_usuarios.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@PreAuthorize("denyAll()")
public class PermissionService implements IPermissionService{
    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public List<Permission> findAll()
    {
        return permissionRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Optional<Permission> findById(Long id)
    {
        return permissionRepository.findById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMIN')")
    public Permission save(Permission permission)
    {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id)
    {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission update(Permission permission)
    {
        return permissionRepository.save(permission);
    }
}
