package com.plataforma_educativa.admnistrador_usuarios.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO (@NotBlank String username, @NotBlank String password){
}
