package br.ifsp.task_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import br.ifsp.task_api.model.Task.Prioridade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Prioridade é obrigatória")
    private Prioridade prioridade;

    @FutureOrPresent
    @NotNull(message = "Data limite é obrigatória")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataLimite;

    private boolean concluida;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    private LocalDateTime criadaEm;
}
