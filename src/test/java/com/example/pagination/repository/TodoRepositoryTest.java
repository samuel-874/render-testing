package com.example.pagination.repository;

import com.example.pagination.entity.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoTestRepo;

    @AfterEach
    void tearDown(){
        todoTestRepo.deleteAll();
    }

    @Test
    void itShouldSelectTodosByTime(){
        // given
        Todo todo = new Todo();
        todo.setWhatTodo("Play Football");
        todo.setTime("After Class");

        todoTestRepo.save(todo);
        // that
        List<Todo> todos = todoTestRepo.findByTime("After Class");

        // then
        assertThat(todos).isNotNull();
        assertThat(todos).extracting(todoList -> todoList.contains(todo));
    }

    @Test
    void shouldCheckIfAllDataWasFlushedAfterFirstTest(){
        List<Todo> todos = todoTestRepo.findAll();
        assertThat(todos).extracting(todoList -> todoList.isEmpty());
    }

}