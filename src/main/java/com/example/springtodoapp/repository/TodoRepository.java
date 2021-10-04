package com.example.springtodoapp.repository;

import java.util.List;

import com.example.springtodoapp.model.entity.Todo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ToDoテーブルのリポジトリインターフェース。
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * 完了フラグをキーにデータを取得。
     */
    public List<Todo> findByIsCompleted(Boolean isCompleted, Sort sort);

}
