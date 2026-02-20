package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Estudiante;
import com.plataforma_educativa.admnistrador_usuarios.repository.IEstudianteRepository;
import com.plataforma_educativa.admnistrador_usuarios.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EstudianteService implements IEstudianteService{
    @Autowired
    private IEstudianteRepository estudianteRepo;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') || hasRole('ROLE_ESTUDIANTE') || hasRole('ROLE_ESTUDIANTE')")
    public List<Estudiante> findAll() {
        return estudianteRepo.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Optional<Estudiante> findById(Long id) {
        return estudianteRepo.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public void deleteById(Long id) {
        estudianteRepo.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Estudiante update(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }
}
