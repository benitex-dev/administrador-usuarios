package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Curso;
import com.plataforma_educativa.admnistrador_usuarios.repository.ICursoRepository;
import com.plataforma_educativa.admnistrador_usuarios.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CursoService implements  ICursoService{

    @Autowired
    private ICursoRepository cursoRepo;

    @Override
    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return cursoRepo.findById(id);
    }

    @Override
    public Curso save(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    public void deleteById(Long id) {
        cursoRepo.deleteById(id);
    }

    @Override
    public Curso update(Curso curso) {
        return cursoRepo.save(curso);
    }
}
