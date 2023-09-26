package cl.ufro.dci.evaluacion1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class represents a videogame
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {
    @Id
    @GeneratedValue

    /**
     * Video game identifier
     */
    private Integer id;

    /**
     * Video game name
     */
    private String name;

    /**
     * Video game genres
     */
    @ElementCollection
    private List<String>genres;

    /**
     * Video console that executes the Video game
     */
    @JsonProperty("video_console")
    private String videoConsole;
}
