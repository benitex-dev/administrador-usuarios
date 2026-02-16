package com.plataforma_educativa.admnistrador_usuarios.controller;

import com.plataforma_educativa.admnistrador_usuarios.model.Permission;
import com.plataforma_educativa.admnistrador_usuarios.model.Role;
import com.plataforma_educativa.admnistrador_usuarios.service.IPermissionService;
import com.plataforma_educativa.admnistrador_usuarios.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
//
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity createRole(@RequestBody Role role) {
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        // Recuperar la Permission/s por su ID
        for (Permission per : role.getPermissionList()) {
            readPermission = permissionService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                //si encuentro, guardo en la lista
                permissionList.add(readPermission);
            }
        }

        role.setPermissionList(permissionList);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }

    @PatchMapping
    public ResponseEntity editRole(@RequestBody Role role){

        //1. primero buscamos el rol que llega por parametro
        Optional<Role> rolEdit = roleService.findByName(role.getRole());

        //2.validamos que tengamos el rol
        if(rolEdit.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        //3.buscamos los permisos que tiene el rol, para editarlos y agregarle más permisos
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        // Recuperar Permission/s por su ID
        for (Permission per : role.getPermissionList()) {
            readPermission = permissionService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                //si encuentro, guardo en la lista
                permissionList.add(readPermission);
            }
        }

        rolEdit.get().setPermissionList(permissionList);
        Role newRole = roleService.save(rolEdit.get());
        return ResponseEntity.ok(newRole);
    }
}
