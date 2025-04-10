package br.ifsp.task_api.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifsp.task_api.dto.TaskRequestDTO;
import br.ifsp.task_api.dto.TaskResponseDTO;
import br.ifsp.task_api.exception.ResourceNotFoundException;
import br.ifsp.task_api.model.Task;
import br.ifsp.task_api.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final ModelMapper modelMapper;

	public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
		this.taskRepository = taskRepository;
		this.modelMapper = modelMapper;
	}
	
	public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
		return taskRepository.findAll(pageable)
                .map(this::convertToResponseDTO);
	}
	
	public TaskResponseDTO getTaskById(Long id) {
		Task task =  taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada: " + id));
		return convertToResponseDTO(task);
	}
	
	
	public Page<TaskResponseDTO> searchTasksByCategoria(String categoria, Pageable pageable) {
		return taskRepository.findByCategoriaContainingIgnoreCase(categoria, pageable);		//pode dar erro!!!
	}
	
	public TaskResponseDTO save(TaskRequestDTO taskDTO) {
		Task task = convertRequestToEntity(taskDTO);
		Task savedTask = taskRepository.save(task);
		return convertToResponseDTO(savedTask);
	}

	
	public TaskResponseDTO convertToResponseDTO(Task task) {
		return modelMapper.map(task, TaskResponseDTO.class);
	}
	
	public Task convertRequestToEntity(TaskRequestDTO taskDTO) {
		return modelMapper.map(taskDTO, Task.class);
	}
	
	public Task convertToEntity(TaskResponseDTO taskDTO) {
		return modelMapper.map(taskDTO, Task.class);
	}
}
