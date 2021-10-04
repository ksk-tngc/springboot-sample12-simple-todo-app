package com.example.springtodoapp.config;

/**
 * DBの初期データ（テストデータ）を登録するコンポーネント。
 * 
 * <p>Spring Boot起動時にコマンドラインとして実行される。
 */
// MEMO: 無効化
// @Component
// @RequiredArgsConstructor
// public class DataLoader implements CommandLineRunner {
// 
//     /** ToDoリポジトリ（コンストラクタインジェクション） */
//     private final TodoRepository todoRepository;
// 
//     /**
//      * Spring Boot起動時にコマンドラインとして実行する処理。
//      * <p>DBの初期データを登録する。
//      */
//     @Override
//     public void run(String... args) throws Exception {
//         // ToDoテーブルの初期データを登録
//         for (int i = 1; i <= 5; i++) {
//             Todo todo = new Todo();
//             todo.setTodoText("ToDo" + i);
//             todo.setIsCompleted(i % 2 == 0);
//             todo.setCreatedDate(LocalDateTime.now());
//             todo.setLastUpdatedDate(LocalDateTime.now());
//             todoRepository.save(todo); // DB登録
//         }
//     }
// 
// }
