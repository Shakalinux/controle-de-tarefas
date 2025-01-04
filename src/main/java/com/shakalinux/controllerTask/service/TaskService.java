package com.shakalinux.controllerTask.service;

import com.shakalinux.controllerTask.model.Task;
import com.shakalinux.controllerTask.model.User;
import com.shakalinux.controllerTask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository ts;

    public List<Task> listAllTask(){
        return ts.findAll();
    }

    public void saveTask(Task task){
        ts.save(task);
    }

    public void deleteTask(Long id){
        ts.deleteById(id);
    }

    public Optional<Task> searchTaskId(Long id){
        return  ts.findById(id);
    }

    public List<Task> listTasksByUser(User user) {
        return ts.findByUser(user);
    }



}
