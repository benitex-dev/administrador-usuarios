package com.plataforma_educativa.admnistrador_usuarios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/homeseg")
    public String secHelloWorld() {

        return "Hola bienvenido a la sección para admnistrar usuarios, roles y permisos.";
    }

    @GetMapping("/homenoseg")
    public String noSecHelloWorld() {

        return "Hola desde endopoint no seguro.";
    }


}
