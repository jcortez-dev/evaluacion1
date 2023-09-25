package cl.ufro.dci.evaluacion1.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Videogame {
    @Id
    @GeneratedValue
    private String name;

    @ElementCollection
    private List<String>genres;
}
