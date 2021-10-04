package com.example.springtodoapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.springtodoapp.model.entity.Todo;
import com.example.springtodoapp.model.form.TodoForm;
import com.example.springtodoapp.repository.TodoRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

/**
 * トップページのコントローラ。
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    /** ToDoテーブルのリポジトリ（コンストラクタインジェクション） */
    private final TodoRepository todoRepository;

    /**
     * トップページ。
     */
    @GetMapping("/")
    public String index(@ModelAttribute TodoForm todoForm, Model model) {
        // 未完了のToDoデータを取得
        List<Todo> incompleteTodos = todoRepository.findByIsCompleted(false, Sort.by(Direction.ASC, "lastUpdatedDate"));
        // 完了したToDoデータを取得
        List<Todo> completeTodos = todoRepository.findByIsCompleted(true, Sort.by(Direction.ASC, "lastUpdatedDate"));
        // それぞれをModelに追加
        model.addAttribute("incompleteTodos", incompleteTodos);
        model.addAttribute("completeTodos", completeTodos);

        return "index";
    }

    /**
     * 追加ボタン。
     */
    @PostMapping(value = "/", params = { "add" })
    public String addTodo(@Validated @ModelAttribute TodoForm todoForm, BindingResult bindingResult, Model model) {
        // バリデーション
        if (bindingResult.hasErrors()) {
            // MEMO: リダイレクトだと、リダイレクト先でバリデーションエラーを認識できない
            // return "redirect:/";
            return index(todoForm, model);
        }

        // 未完了のToDoとして登録する
        LocalDateTime now = LocalDateTime.now();
        Todo todo = new Todo(null, todoForm.getTodoText(), false, now, now); // IDは自動採番なのでnullでOK
        todoRepository.save(todo); // DB登録

        return "redirect:/";
    }

    /**
     * 完了ボタン。
     */
    @PostMapping(value = "/", params = { "complete" })
    public String completeTodo(@RequestParam("complete") Long id) {
        // 完了フラグを「true」に更新する
        Todo todo = todoRepository.findById(id).get(); // 既存「id」データ取得
        todo.setIsCompleted(true); // 完了フラグを更新
        todo.setLastUpdatedDate(LocalDateTime.now()); // 最終更新日時を更新
        todoRepository.save(todo); // DBに反映（UPDATE）

        return "redirect:/";
    }

    /**
     * 削除ボタン。
     */
    @PostMapping(value = "/", params = { "delete" })
    public String deleteTodo(@RequestParam("delete") Long id) {
        // ToDoテーブルから指定「id」のデータを削除する
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    /**
     * 戻すボタン。
     */
    @PostMapping(value = "/", params = { "back" })
    public String backTodo(@RequestParam("back") Long id) {
        // 完了フラグを「false」に更新する
        Todo todo = todoRepository.findById(id).get(); // 既存「id」データ取得
        todo.setIsCompleted(false); // 完了フラグを更新
        todo.setLastUpdatedDate(LocalDateTime.now()); // 最終更新日時を更新
        todoRepository.save(todo); // DBに反映（UPDATE）

        return "redirect:/";
    }

}
