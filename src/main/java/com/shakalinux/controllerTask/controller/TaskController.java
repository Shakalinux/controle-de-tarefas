package com.shakalinux.controllerTask.controller;

import com.shakalinux.controllerTask.model.Task;
import com.shakalinux.controllerTask.model.TaskStatus;
import com.shakalinux.controllerTask.model.User;
import com.shakalinux.controllerTask.service.TaskService;
import com.shakalinux.controllerTask.service.UserService;
import com.shakalinux.controllerTask.utils.ServiceEmail;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    private ServiceEmail serviceEmail = new ServiceEmail();

    @GetMapping
    public String getHomeTask(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        List<Task> tasks = taskService.listTasksByUser(user);
        for (Task task : tasks) {
            if (task.getTaskImage() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(task.getTaskImage());
                task.setTaskImage64(imagemBase64);
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "homeTask";
    }



    @GetMapping("/createTask")
    public String getCreateTask(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }

    @PostMapping("/createTask")
    public String createTask(
            @Valid @ModelAttribute("task") Task task,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            Model model) {
        if (result.hasErrors()) {
            return "createTask";
        }

        if (file.isEmpty()) {
            model.addAttribute("fileError", "A imagem é obrigatória.");
            return "createTask";
        }
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        try {
            task.setUser(loggedInUser);
            task.setTaskImage(file.getBytes());
            taskService.saveTask(task);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/task/createTask?error=uploadError";
        }

        return "redirect:/task";
    }

    @GetMapping("/{id}")
    public String detailTask(@PathVariable Long id, Model model) {
        Optional<Task> taskOpt = taskService.searchTaskId(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            if (task.getTaskImage() != null) {
                String imagemBase64 = Base64.getEncoder().encodeToString(task.getTaskImage());
                task.setTaskImage64(imagemBase64);
            }

            if (task.getDate() != null) {
                long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), task.getDate());
                model.addAttribute("daysRemaining", daysRemaining);
            }

            model.addAttribute("task", task);
            return "detalhesTask";
        } else {
            return "redirect:/task?error=notFound";
        }
    }


    @GetMapping("/editTask/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {
        Optional<Task> task = taskService.searchTaskId(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "detalhesTask";
        } else {
            return "redirect:/task?error=notFound";
        }
    }

    @PostMapping("/editTask/{id}")
    public String updateTask(@PathVariable("id") Long id,
                             @RequestParam("taskName") String taskName,
                             @RequestParam("description") String description,
                             @RequestParam("date") LocalDate date,
                             @RequestParam("status") TaskStatus status) {
        Optional<Task> existingTaskOpt = taskService.searchTaskId(id);

        if (!existingTaskOpt.isPresent()) {
            return "redirect:/task?error=taskNotFound";
        }

        Task existingTask = existingTaskOpt.get();

        existingTask.setTaskName(taskName);
        existingTask.setDescription(description);
        existingTask.setDate(date);
        existingTask.setStatus(status);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        existingTask.setUser(user);

        taskService.saveTask(existingTask);

        return "redirect:/task";
    }


    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/task";
    }

    @PostMapping("/lembrete")
    public String scheduleEmail(@RequestParam Long taskId,
                                @RequestParam String reminderDate) {
        LocalDateTime reminderDateTime = LocalDateTime.parse(reminderDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        if (user == null) {
            return "redirect:/task?error=usuarioNaoEncontrado";
        }

        Task task = taskService.searchTaskId(taskId).orElse(null);
        if (task == null) {
            return "redirect:/task?error=tarefaNaoEncontrada";
        }

        String email = user.getEmail();
        String nomeTarefa = task.getTaskName();
        String dataLembrete = reminderDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        long delay = java.time.Duration.between(LocalDateTime.now(), reminderDateTime).toMillis() / 1000;

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            serviceEmail.servicoEmail(email, user.getUsername(), nomeTarefa, dataLembrete);
        }, delay, TimeUnit.SECONDS);

        return "redirect:/task";
    }

    @GetMapping("/concluir/{id}")
    public String concluirTarefa(@PathVariable("id") Long id) {
        Optional<Task> existingTaskOpt = taskService.searchTaskId(id);

        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setStatus(TaskStatus.CONCLUIDO);
            taskService.saveTask(existingTask);
        }

        return "redirect:/task";
    }

}
