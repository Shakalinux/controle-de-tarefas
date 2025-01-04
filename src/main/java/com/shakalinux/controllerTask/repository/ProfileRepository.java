package com.shakalinux.controllerTask.repository;

import com.shakalinux.controllerTask.model.Profile;
import com.shakalinux.controllerTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
}
