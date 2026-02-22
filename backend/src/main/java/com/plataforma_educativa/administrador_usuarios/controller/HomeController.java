package com.plataforma_educativa.administrador_usuarios.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
public class HomeController {

    @GetMapping("/homeseg")
    @PreAuthorize("hasAuthority('READ') ")
    public String secHelloWorld() {
        return "Hola bienvenido a la sección para admnistrar usuarios, roles y permisos.";
    }

    @GetMapping("/homenoseg")
    @PreAuthorize("permitAll()")
    public String noSecHelloWorld() {
        return "Hola desde endopoint no seguro.";
    }


}
