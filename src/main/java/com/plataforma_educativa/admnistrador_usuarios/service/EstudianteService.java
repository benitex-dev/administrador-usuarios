package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Estudiante;
import com.plataforma_educativa.admnistrador_usuarios.repository.IEstudianteRepository;
import com.plataforma_educativa.admnistrador_usuarios.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EstudianteService implements IEstudianteService{
    @Autowired
    private IEstudianteRepository estudianteRepo;

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepo.findAll();
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return estudianteRepo.findById(id);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    @Override
    public void deleteById(Long id) {
        estudianteRepo.deleteById(id);
    }

    @Override
    public Estudiante update(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }
}
