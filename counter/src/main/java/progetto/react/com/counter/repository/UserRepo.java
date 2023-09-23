package progetto.react.com.counter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progetto.react.com.counter.entity.TodoList;
import progetto.react.com.counter.entity.Utenti;

import java.util.List;

public interface UserRepo extends JpaRepository<Utenti, Long> {
    boolean existsByUserName(String userName);
}
