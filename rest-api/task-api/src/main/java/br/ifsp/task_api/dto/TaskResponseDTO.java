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
public class TaskResponseDTO {
	
    private Long id;

    private String titulo;

    private String descricao;

    private Prioridade prioridade;

    private LocalDate dataLimite;

    private boolean concluida;

    private String categoria;

    private LocalDateTime criadaEm;

}
