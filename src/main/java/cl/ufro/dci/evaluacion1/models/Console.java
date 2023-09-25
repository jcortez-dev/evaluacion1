package cl.ufro.dci.evaluacion1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

public class Console {
    private Integer id;
    private String name;
    private List<Videogame> videogames;
}
