package progetto.react.com.counter.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String todo ;

    public Todo(String todo){
        super();
        this.todo = todo;
    }


}
