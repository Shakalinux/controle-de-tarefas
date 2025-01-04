package com.shakalinux.controllerTask.repository;

import com.shakalinux.controllerTask.model.Task;
import com.shakalinux.controllerTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
