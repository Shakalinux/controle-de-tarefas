package com.shakalinux.controllerTask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    @NotBlank(message = "O nome da tarefa é obrigatório")
    @Size(max = 60, message = "O nome da tarefa deve ter no máximo 60 caracteres")
    @Column(nullable = false, columnDefinition = "VARCHAR(60)")
    private String taskName;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "A data é obrigatória")
    @FutureOrPresent(message = "A data da tarefa deve ser uma data futura ou presente")
    @Column(nullable = false)
    private LocalDate date;

    @Transient
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate reminderDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da tarefa é obrigatório!")
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.EM_ANDAMENTO;

    @Lob
    private byte[] taskImage;

    @Transient
    private String taskImage64;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
