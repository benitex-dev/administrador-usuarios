package com.plataforma_educativa.administrador_usuarios.controller;

import com.plataforma_educativa.administrador_usuarios.model.Permission;
import com.plataforma_educativa.administrador_usuarios.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions(){
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .notFound()
                        .build());
    }


    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission){
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

}
