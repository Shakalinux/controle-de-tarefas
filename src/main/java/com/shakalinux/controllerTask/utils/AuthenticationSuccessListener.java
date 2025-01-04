package com.shakalinux.controllerTask.utils;

import com.shakalinux.controllerTask.model.Acess;
import com.shakalinux.controllerTask.model.User;
import com.shakalinux.controllerTask.repository.AcessRepository;
import com.shakalinux.controllerTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AuthenticationSuccessListener {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcessRepository acessRepository;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        if (user != null) {
            Acess acess = new Acess();
            acess.setUser(user);
            acess.setDataAcesso(LocalDate.now());
            acessRepository.save(acess);
        }
    }
}
