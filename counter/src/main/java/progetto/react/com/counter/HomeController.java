package progetto.react.com.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progetto.react.com.counter.entity.TodoList;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private TodoListRepo repo;

    @ModelAttribute
    public void modelData(Model m){
        m.addAttribute("name", "TodoList");
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/GetTodoList")
    public List<TodoList> getTodoList(Model m){
       return  repo.findAll();
    }

    @PostMapping("/addTodo")
    public TodoList addTodo(@RequestParam String todo) {
        TodoList t = new TodoList(todo);
        return repo.save(t);
    }
}
