package com.example.pagination;

import com.example.pagination.entity.Todo;
import com.example.pagination.repository.TodoRepository;
import com.example.pagination.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class PaginationApplication {
	private final TodoRepository todoRepository;
	private final TodoService todoservice;

	public static void main(String[] args) {
		SpringApplication.run(PaginationApplication.class, args);
	}


	@PostMapping("/add-todo")
	public Todo addTodo(@RequestBody Todo todo){
		return todoservice.saveTodo(todo);
	}
	@PostMapping("/add-todos")
	public List<Todo> addTodo(@RequestBody List<Todo> todos){
		return todoservice.saveAllTodo(todos);
	}


	@GetMapping("/all-todos")
	public ResponseEntity<Object> allTodos(Pageable pageable){
//	Pageable pageable = PageRequest.of(1, 5, Sort.Direction.ASC,"id");
		return todoservice.findAll(pageable);
	}

}
