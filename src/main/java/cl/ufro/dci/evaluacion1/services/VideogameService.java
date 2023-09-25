package cl.ufro.dci.evaluacion1.services;

import cl.ufro.dci.evaluacion1.models.Videogame;
import cl.ufro.dci.evaluacion1.repositories.VideogameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VideogameService {
    private final VideogameRepository videogameRepository;

    private final String [] VIDEOGAMES_PATHS = {
            "src\\main\\java\\cl\\ufro\\dci\\evaluacion1\\utils\\GBA.json",
    "src\\main\\java\\cl\\ufro\\dci\\evaluacion1\\utils\\N64.json",
    "src\\main\\java\\cl\\ufro\\dci\\evaluacion1\\utils\\PS2.json"};


    public List<Videogame> loadVideogames() throws IOException {
        for (String path: VIDEOGAMES_PATHS
             ) {
            readJsonFile(path);
        }
        return videogameRepository.findAll();
    }

    public List<Videogame> readJsonFile(String pathname) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Videogame[] videoGames = objectMapper.readValue(new File(pathname), Videogame[].class);
        return videogameRepository.saveAll(Arrays.asList(videoGames));
    }

    public Videogame searchByName(String name){
        return videogameRepository.findByName(name).get();
    }

    /*
    public List<Videogame> searchRandomVideogames(String console){
        List<Videogame> randomVideogames = new ArrayList<>();
        Optional<Videogame> list = videogameRepository.findByVideo_console(console);
        Random random = new Random();
        list.add()
        return
    }
    */

}
