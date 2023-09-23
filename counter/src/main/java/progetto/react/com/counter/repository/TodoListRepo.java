package progetto.react.com.counter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progetto.react.com.counter.entity.TodoList;

import java.util.List;

public interface TodoListRepo extends JpaRepository<TodoList, Integer> {
    List<TodoList> findTodosByUserId(Long userId);

}
