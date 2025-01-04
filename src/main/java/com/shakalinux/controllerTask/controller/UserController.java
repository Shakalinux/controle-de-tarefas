package com.shakalinux.controllerTask.controller;

import com.shakalinux.controllerTask.model.Profile;
import com.shakalinux.controllerTask.model.User;
import com.shakalinux.controllerTask.service.ProfileService;
import com.shakalinux.controllerTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProfileService profileService;


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastro")
    public String getCadastre(Model model) {
        model.addAttribute("user", new User());
        return "user/cadastre";
    }

    @PostMapping("/cadastro")
    public String registerUser(@ModelAttribute @Validated User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/cadastre";
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Nome de usuário já existe!");
            return "user/cadastre";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Profile profile = new Profile();
        profileService.saveProfile(profile);
        user.setProfile(profile);
        userService.saveUser(user);
        model.addAttribute("message", "Usuário cadastrado com sucesso!");
        return "user/login";
    }



    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute @Validated User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/login";
        }
        User existingUser = userService.findByUsername(user.getUsername());


        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            model.addAttribute("error", "Nome de usuário ou senha inválidos. Tente novamente ou cadastre-se.");
            return "user/login";
        }


        return "redirect:/profile";
    }
}
