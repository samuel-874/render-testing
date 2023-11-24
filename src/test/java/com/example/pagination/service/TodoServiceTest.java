package com.example.pagination.service;

import com.example.pagination.entity.Todo;
import com.example.pagination.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoTestRepo;

    private TodoService todoTestService;
    @Captor
    private ArgumentCaptor<List<Todo>> argumentCaptor;

    @BeforeEach
    void setUp(){
        todoTestService = new TodoService(todoTestRepo);
    }



    @Test
    void if_findAll_canGetAllTodos() {
        //when
        todoTestService.findAll(Pageable.ofSize(10));
        //then
        verify(todoTestRepo).findAll(Pageable.ofSize(10));
    }

    @Test
    void saveTodo() {

        // given
        Todo todo = Todo.builder()
                .whatTodo("Sweep")
                .time("In the morning")
                .build();

        // mock behaviour
        when(todoTestService.saveTodo(todo)).thenReturn(todo);

        // when
       Todo savedValue = todoTestService.saveTodo(todo);
        ArgumentCaptor<Todo> todoArgumentCaptor =
                ArgumentCaptor.forClass(Todo.class);

        verify(todoTestRepo)
                .save(todoArgumentCaptor.capture());

        Todo savedTodo = todoArgumentCaptor.getValue();
        // then
        assertAll(
               () -> assertThat(savedValue).isNotNull(),
               () -> assertThat(savedTodo).isEqualTo(todo),
               () -> assertThat(todo).extracting(todo1 -> todo1.getId() > 0),
               () -> assertThrows(IllegalArgumentException.class,() -> todoTestService.findTodo(10L))
       );
    }

    @Test
    void saveAllTodo() {

        // given
        List<Todo> todos = new ArrayList<>();

        for(int c = 0; c < 10; c++){
            Todo newTodo = Todo.builder().whatTodo("todo "+ c).time("time "+c).build();
            todos.add(newTodo);
        }

        //mock behaviour
        when(todoTestService.saveAllTodo(todos)).thenReturn(todos);

        //when
        todoTestService.saveAllTodo(todos);

        verify(todoTestRepo).saveAll(argumentCaptor.capture());
        List<Todo> savedTodoList = argumentCaptor.getValue();

        //then
        assertAll(
                () -> assertThat(todos).isEqualTo(savedTodoList),
                () -> assertThat(savedTodoList).extracting( todosList -> todosList.contains(todos.get(0)))
        );
    }
}