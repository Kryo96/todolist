package progetto.react.com.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progetto.react.com.counter.models.User;
import progetto.react.com.counter.payload.request.AddTodoRequest;
import progetto.react.com.counter.models.Todo;
import progetto.react.com.counter.repository.TodoRepo;
import progetto.react.com.counter.repository.UserRepository;
import progetto.react.com.counter.security.services.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepo repo;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void modelData(Model m){
        m.addAttribute("name", "TodoList");
    }

    @GetMapping("/GetTodoList")
    @CrossOrigin
    public ResponseEntity<?> getTodoList(){
        //TODO filtrare per todo del singolo utente
        List<Todo> userTodoList = repo.findAll();
        return ResponseEntity.ok(userTodoList.stream().map(Todo::getTodo));
    }

    @PostMapping("/addTodo")
    @CrossOrigin
    public ResponseEntity<?> addTodo(@RequestBody AddTodoRequest requestData) {
        //TODO spostare in una classe di utility queste righe per recuperare l'utente loggato
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl authPrincipal = (UserDetailsImpl) auth.getPrincipal();
        Optional<User> user = userRepository.findById(authPrincipal.getId());
        if(user.isPresent()){
            Todo t = new Todo(requestData.getTodo(), user.get());
            repo.save(t);
            return ResponseEntity.ok("todo sucessfully saved");
        }
        return ResponseEntity.notFound().build();
    }

}
