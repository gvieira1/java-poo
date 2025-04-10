package br.ifsp.task_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.task_api.dto.TaskResponseDTO;
import br.ifsp.task_api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	Page<TaskResponseDTO> findByCategoriaContainingIgnoreCase(String categoria, Pageable pageable);
	
}
