package cl.ufro.dci.evaluacion1.controllers;

import cl.ufro.dci.evaluacion1.models.Videogame;
import cl.ufro.dci.evaluacion1.services.VideogameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class VideogameController {
    private final VideogameService videogameService;

    @GetMapping("")
    public List<Videogame> loadVideogames() throws IOException {
        return videogameService.getVideogames();
    }

    @GetMapping("consoles/{console_abreviation}/random_games")
    public Map<String, List<String>> findTwoVideogames(@PathVariable String console_abreviation){
        return videogameService.searchRandomVideogames(console_abreviation);
    }

    @GetMapping("games")
    public Map<String, String> findByName(@RequestParam String name){
        return videogameService.searchByName(name);
    }
}
