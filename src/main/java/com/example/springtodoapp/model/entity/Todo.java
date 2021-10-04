package com.example.springtodoapp.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ToDoテーブルのエンティティ。
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    /** ID */
    @Id
    @GeneratedValue
    private Long id;

    /** ToDoテキスト */
    @NotBlank
    @NotNull
    private String todoText;

    /** 完了フラグ */
    @NotNull
    private Boolean isCompleted = false;

    /** 作成日時 */
    @NotNull
    private LocalDateTime createdDate;

    /** 最終更新日時 */
    @NotNull
    private LocalDateTime lastUpdatedDate;

}
