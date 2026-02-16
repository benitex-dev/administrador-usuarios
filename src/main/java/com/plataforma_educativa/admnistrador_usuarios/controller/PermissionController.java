package com.plataforma_educativa.admnistrador_usuarios.controller;

import com.plataforma_educativa.admnistrador_usuarios.model.Permission;
import com.plataforma_educativa.admnistrador_usuarios.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/permissions")
//@PreAuthorize("denyAll()")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<List<Permission>> getAllPermissions(){
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .notFound()
                        .build());
    }


    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMIN')")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

}
