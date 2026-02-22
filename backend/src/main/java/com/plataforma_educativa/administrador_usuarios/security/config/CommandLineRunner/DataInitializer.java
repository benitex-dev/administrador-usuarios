package com.plataforma_educativa.administrador_usuarios.security.config.CommandLineRunner;

import com.plataforma_educativa.administrador_usuarios.model.Role;
import com.plataforma_educativa.administrador_usuarios.model.UserSec;
import com.plataforma_educativa.administrador_usuarios.repository.IRoleRepository;
import com.plataforma_educativa.administrador_usuarios.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository; // Lo necesitamos para el Role

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // 1. Chequeamos si el admin ya existe para no duplicarlo
        if (userRepository.findUserEntityByUsername("admin").isEmpty()) {

            // 2. Buscamos el rol que cargamos en el import.sql
            Role adminRole = roleRepository.findRoleEntityByRole("ADMINISTRADOR")
                    .orElseThrow(() -> new RuntimeException("Error: El rol ADMINISTRADOR no existe."));

            // 3. Creamos el usuario encriptando la clave
            UserSec adminUser = UserSec.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234")) // <--- ACÁ SE ENCRIPTA
                    .enabled(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .credentialNotExpired(true)
                    .rolesList(Set.of(adminRole))
                    .build();

            userRepository.save(adminUser);
            System.out.println("✅ Usuario ADMIN creado y encriptado con éxito.");
        }
    }
}
