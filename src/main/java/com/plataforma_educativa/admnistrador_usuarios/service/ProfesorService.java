package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Profesor;
import com.plataforma_educativa.admnistrador_usuarios.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProfesorService implements IProfesorService{

    @Autowired
    private IProfesorRepository profeRepo;

    @Override
    public List<Profesor> findAll() {
        return profeRepo.findAll();
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        return profeRepo.findById(id);
    }

    @Override
    public Profesor save(Profesor profe) {
        return profeRepo.save(profe);
    }

    @Override
    public void deleteById(Long id) {
        profeRepo.deleteById(id);
    }

    @Override
    public Profesor update(Profesor profe) {
        return profeRepo.save(profe);
    }
}
