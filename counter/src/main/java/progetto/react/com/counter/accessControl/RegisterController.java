package progetto.react.com.counter.accessControl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import progetto.react.com.counter.dto.RegReq;
import progetto.react.com.counter.entity.Utenti;
import progetto.react.com.counter.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class RegisterController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private Long vaffanculo = 0;


    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<?> register(@RequestBody RegReq regReq){
        if (userRepository.existsByUserName(regReq.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        Utenti user = new Utenti();
        user.setId(vaffanculo);
        vaffanculo++;
        user.setUserName(regReq.getUsername());
        user.setPsw(passwordEncoder.encode(regReq.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Registration successful");
    }
}
