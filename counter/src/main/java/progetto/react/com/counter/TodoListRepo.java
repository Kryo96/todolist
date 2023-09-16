package progetto.react.com.counter;

import org.springframework.data.jpa.repository.JpaRepository;
import progetto.react.com.counter.entity.TodoList;

public interface TodoListRepo extends JpaRepository<TodoList, Integer> {
}
