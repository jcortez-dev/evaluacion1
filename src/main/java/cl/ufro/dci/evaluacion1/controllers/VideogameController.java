package cl.ufro.dci.evaluacion1.controllers;

import cl.ufro.dci.evaluacion1.models.Videogame;
import cl.ufro.dci.evaluacion1.services.VideogameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class VideogameController {
    private final VideogameService videogameService;

    @GetMapping("")
    public List<Videogame> loadVideogames() throws IOException {
        return videogameService.loadVideogames();
    }
}
