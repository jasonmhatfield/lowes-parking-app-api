package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.AppUser;
import com.lowes.lowesparkingappapi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }
}
