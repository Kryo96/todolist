package progetto.react.com.counter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progetto.react.com.counter.models.Todo;
import progetto.react.com.counter.models.User;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

}
