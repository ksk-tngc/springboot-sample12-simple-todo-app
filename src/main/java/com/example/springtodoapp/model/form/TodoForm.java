package com.example.springtodoapp.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * ToDoアプリのFormクラス。
 */
@Data
public class TodoForm {

    /** ToDo入力テキスト */
    @NotBlank(message = "ToDoを入力してください")
    @NotNull
    private String todoText;

}
