package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.Curso;
import com.plataforma_educativa.admnistrador_usuarios.repository.ICursoRepository;
import com.plataforma_educativa.admnistrador_usuarios.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CursoService implements  ICursoService{

    @Autowired
    private ICursoRepository cursoRepo;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR') || hasRole('ROLE_ESTUDIANTE')||hasRole('ROLE_PROFESOR')")
    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Optional<Curso> findById(Long id) {
        return cursoRepo.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Curso save(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public void deleteById(Long id) {
        cursoRepo.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Curso update(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public Optional<Curso> findCursoEntityByNombreCurso(String nombreCurso) {
        return cursoRepo.findCursoEntityByNombreCurso(nombreCurso);
    }
}
