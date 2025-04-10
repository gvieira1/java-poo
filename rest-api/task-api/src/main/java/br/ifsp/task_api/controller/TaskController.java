package br.ifsp.task_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.task_api.dto.TaskRequestDTO;
import br.ifsp.task_api.dto.TaskResponseDTO;
import br.ifsp.task_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Tarefas", description = "API para gerenciamento de tarefas")
@RestController
@RequestMapping("/api/task")
@Validated
public class TaskController {
		
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
    @Operation(summary = "Listar todas as tarefas", description = "Retorna uma lista paginada de todas as tarefas cadastrados no sistema")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Tarefas encontrados com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> getAllTasks(Pageable pageable) {
    	return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }
    
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
    	return ResponseEntity.ok(taskService.getTaskById(id));
    }
    
    @Operation(summary = "Buscar tarefas por categoria", description = "Retorna uma lista paginada de tarefas cuja categoria contém o termo pesquisado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponseDTO>> searchTasksByCategoria(@RequestParam String categoria, Pageable pageable) {
    	return ResponseEntity.ok(taskService.searchTasksByCategoria(categoria, pageable));
    }
    
    @Operation(summary = "Cria tarefa", description = "Cria uma nova tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa criada")
    })
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
        return ResponseEntity.ok(taskService.save(dto));
    }

	
}
