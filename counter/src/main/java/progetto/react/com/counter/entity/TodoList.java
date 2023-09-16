package progetto.react.com.counter.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TodoList {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numero;

    private String todo ;

    public TodoList(String todo){
        super();
        this.todo = todo;
    }

}
