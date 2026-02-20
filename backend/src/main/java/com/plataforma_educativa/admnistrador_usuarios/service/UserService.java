package com.plataforma_educativa.admnistrador_usuarios.service;

import com.plataforma_educativa.admnistrador_usuarios.model.UserSec;
import com.plataforma_educativa.admnistrador_usuarios.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return userRepository.findById(id);
    }



    @Override
    @PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMINISTRADOR')")
    public UserSec save(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE') && hasRole('ROLE_ADMINISTRADOR')")
    public void update(UserSec userSec) {
        save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);

    }
}
