package cl.ufro.dci.evaluacion1.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videogame {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ElementCollection
    private List<String>genres;
    private String video_console;
}
