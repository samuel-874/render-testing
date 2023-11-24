package com.example.pagination.service;

import com.example.pagination.entity.Todo;
import com.example.pagination.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public ResponseEntity<Object> findAll(Pageable pageable){
        Page<Todo> todos  = todoRepository.findAll(pageable);

        return  ResponseEntity.ok(todos);
    }

    public Todo saveTodo(Todo todo){
        return todoRepository.save(todo);
    }

       public List<Todo> saveAllTodo(List<Todo> todoList){
        return todoRepository.saveAll(todoList);
    }

       public Todo findTodo(long todoId){
           Optional<Todo> optionalTodo = todoRepository.findById(todoId);

           if(optionalTodo.isEmpty()){
               throw new IllegalArgumentException("Id passed was invalid");
           }

           return optionalTodo.get();
    }




}
