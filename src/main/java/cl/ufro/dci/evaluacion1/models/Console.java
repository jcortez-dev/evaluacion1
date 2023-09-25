package cl.ufro.dci.evaluacion1.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Console {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany
    private List<Videogame> videogames;
}
