package progetto.react.com.counter.accessControl;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import progetto.react.com.counter.dto.LoginRequest;
import progetto.react.com.counter.entity.Utenti;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@RestController
public class LoginController
{


    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // In a real application, you would validate the credentials here.
        // For simplicity, let's assume successful authentication.
        Utenti authenticatedUser = new Utenti();
        authenticatedUser.setUserName(loginRequest.getUsername()); // Set the username
        String jwt = generateJwt(authenticatedUser);
        return ResponseEntity.ok(Map.of("token", jwt)); // Return the JWT in the response
    }


    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateJwt(Utenti user) {
        int EXPIRATION_TIME = 8600000;
        return Jwts.builder()
                .setSubject(user.getUserName()) // Set the subject to be the username
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration time
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();  // Sign with a secret key                .compact(); // Compact and finalize the token
    }

}
