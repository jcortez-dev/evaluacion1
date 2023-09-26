package cl.ufro.dci.evaluacion1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

    @JsonProperty("video_console")
    private String videoConsole;
}
