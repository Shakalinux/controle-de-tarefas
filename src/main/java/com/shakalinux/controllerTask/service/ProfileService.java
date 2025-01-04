package com.shakalinux.controllerTask.service;

import com.shakalinux.controllerTask.model.Profile;
import com.shakalinux.controllerTask.model.User;
import com.shakalinux.controllerTask.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> listAllProfile(){
        return profileRepository.findAll();
    }

    public void saveProfile(Profile profile){
        profileRepository.save(profile);
    }

    public void deletarProfile(Long id){
        profileRepository.deleteById(id);
    }

    public Optional<Profile> findProfileById(Long id){
        return profileRepository.findById(id);
    }
    public Profile findByUser(User user) {
        return profileRepository.findByUser(user);
    }
}
