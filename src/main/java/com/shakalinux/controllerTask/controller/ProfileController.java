package com.shakalinux.controllerTask.controller;

import com.shakalinux.controllerTask.model.*;
import com.shakalinux.controllerTask.repository.AcessRepository;
import com.shakalinux.controllerTask.repository.TaskRepository;
import com.shakalinux.controllerTask.repository.UserRepository;
import com.shakalinux.controllerTask.service.ProfileService;
import com.shakalinux.controllerTask.service.TaskService;
import com.shakalinux.controllerTask.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcessRepository acessRepository;

    @Autowired
    private TaskRepository taskService;

    @Autowired
    private UserService userService;



    @GetMapping
    public String getProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "Usuário não encontrado.");
            return "error";
        }

        List<Acess> acess = acessRepository.findByUser(user);
        Set<LocalDate> diasAcessados = new HashSet<>();

        for (Acess acesso : acess) {
            diasAcessados.add(acesso.getDataAcesso());
        }
        model.addAttribute("diasAcessados", diasAcessados.size());

        List<Task> tasks = taskService.findByUser(user);
        int totalTasks = tasks.size();
        int completedTasks = (int) tasks.stream().filter(task -> task.getStatus() == TaskStatus.CONCLUIDO).count();
        int inProgressTasks = (int) tasks.stream().filter(task -> task.getStatus() == TaskStatus.EM_ANDAMENTO).count();
        int notCompletedTasks = totalTasks - completedTasks - inProgressTasks;

        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);
        model.addAttribute("notCompletedTasks", notCompletedTasks);


        user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "Usuário não encontrado.");
            return "error";
        }


        Profile profile = profileService.findByUser(user);
        if (profile == null) {
            model.addAttribute("error", "Perfil não encontrado.");
            return "error";
        }


        if (profile.getAvatar() != null && profile.getAvatar().length > 0) {
            String imagemBase64 = Base64.getEncoder().encodeToString(profile.getAvatar());
            profile.setAvatar64(imagemBase64);
        }

        if (profile.getImagePrincipal() != null && profile.getImagePrincipal().length > 0) {
            String imagemPrincipal64 = Base64.getEncoder().encodeToString(profile.getImagePrincipal());
            profile.setImagePrincipal64(imagemPrincipal64);
        }

        model.addAttribute("profile", profile);
        model.addAttribute("user", user);

        return "profile/profile";
    }

    @PostMapping
    public String updateProfile(@ModelAttribute @Valid Profile profile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profile";
        }
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);

            if (user == null) {
                return "redirect:/profile?error=userNotFound";
            }


            Profile existingProfile = profileService.findByUser(user);
            if (existingProfile == null) {
                return "redirect:/profile?error=profileNotFound";
            }

            if (profile.getAvatarFile() != null && !profile.getAvatarFile().isEmpty()) {
                existingProfile.setAvatar(profile.getAvatarFile().getBytes());
            }

            if (profile.getImagePrincipalFile() != null && !profile.getImagePrincipalFile().isEmpty()) {
                existingProfile.setImagePrincipal(profile.getImagePrincipalFile().getBytes());
            }

            existingProfile.setNickname(profile.getNickname());
            existingProfile.setFrase(profile.getFrase());
            profileService.saveProfile(existingProfile);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/profile?error=uploadError";
        }

        return "redirect:/profile";
    }



}







