package progetto.react.com.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progetto.react.com.counter.entity.TodoList;
import progetto.react.com.counter.entity.Utenti;
import progetto.react.com.counter.repository.TodoListRepo;
import progetto.react.com.counter.repository.UserRepo;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private TodoListRepo repo;
    private UserRepo userRepo;

    @ModelAttribute
    public void modelData(Model m){
        m.addAttribute("name", "TodoList");
    }

    @GetMapping("/GetTodoList")
    @CrossOrigin
    public ResponseEntity<?> getTodoList(@AuthenticationPrincipal Utenti utenti){
        Long id = utenti.getId();
        List<TodoList> userTodoList = repo.findTodosByUserId(id);
        return ResponseEntity.ok(userTodoList);
    }

    @PostMapping("/addTodo")
    @CrossOrigin
    public TodoList addTodo(@RequestBody Map<String, String> requestData) {
        String todo = requestData.get("todo");
        TodoList t = new TodoList(todo);
        return repo.save(t);
    }

}
