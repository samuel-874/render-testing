package com.example.pagination.repository;

import com.example.pagination.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long>, PagingAndSortingRepository<Todo,Long>{

    List<Todo> findByTime(String time);
}
