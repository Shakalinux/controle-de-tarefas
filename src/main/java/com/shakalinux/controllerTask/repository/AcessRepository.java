package com.shakalinux.controllerTask.repository;

import com.shakalinux.controllerTask.model.Acess;
import com.shakalinux.controllerTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AcessRepository extends JpaRepository<Acess, Long> {
    List<Acess> findByUserAndDataAcessoBetween(User user, LocalDate startDate, LocalDate endDate);
    List<Acess> findByUser(User user);
}
