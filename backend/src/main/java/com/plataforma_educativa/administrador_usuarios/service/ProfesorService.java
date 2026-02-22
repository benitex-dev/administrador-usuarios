package com.plataforma_educativa.administrador_usuarios.service;

import com.plataforma_educativa.administrador_usuarios.model.Profesor;
import com.plataforma_educativa.administrador_usuarios.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@PreAuthorize("denyAll()")
public class ProfesorService implements IProfesorService{

    @Autowired
    private IProfesorRepository profeRepo;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') || hasRole('ROLE_ESTUDIANTE')||hasRole('ROLE_PROFESOR')")
    public List<Profesor> findAll() {
        return profeRepo.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Optional<Profesor> findById(Long id) {
        return profeRepo.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Profesor save(Profesor profe) {
        return profeRepo.save(profe);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public void deleteById(Long id) {
        profeRepo.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Profesor update(Profesor profe) {
        return profeRepo.save(profe);
    }
}
