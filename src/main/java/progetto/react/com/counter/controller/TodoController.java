package progetto.react.com.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progetto.react.com.counter.payload.request.AddTodoRequest;
import progetto.react.com.counter.models.Todo;
import progetto.react.com.counter.repository.TodoRepo;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepo repo;

    @ModelAttribute
    public void modelData(Model m){
        m.addAttribute("name", "TodoList");
    }

    @GetMapping("/GetTodoList")
    @CrossOrigin
    public ResponseEntity<?> getTodoList(){
        List<Todo> userTodoList = repo.findAll();
        return ResponseEntity.ok(userTodoList);
    }

    @PostMapping("/addTodo")
    @CrossOrigin
    public Todo addTodo(@RequestBody AddTodoRequest requestData) {
        Todo t = new Todo(requestData.getTodo());
        return repo.save(t);
    }

}
