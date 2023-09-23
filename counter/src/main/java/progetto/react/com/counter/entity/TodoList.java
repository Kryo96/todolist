package progetto.react.com.counter.entity;


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
public class TodoList {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "Utenti_id")
    private Utenti utenti;

    private String todo ;

    public TodoList(String todo){
        super();
        this.todo = todo;
    }


}
