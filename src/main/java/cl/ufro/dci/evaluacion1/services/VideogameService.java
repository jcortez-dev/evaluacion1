package cl.ufro.dci.evaluacion1.services;

import cl.ufro.dci.evaluacion1.models.Videogame;
import cl.ufro.dci.evaluacion1.repositories.VideogameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
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


    @PostConstruct
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

    public Map<String, String> searchByName(String name){
        return videogameRepository.findByName(name).isPresent() ?
                jsonParser(videogameRepository.findByName(name).get().getVideoConsole() + " - " + String.join(" - ", videogameRepository.findByName(name).get().getGenres())) :
                jsonParser("Juego no encontrado en nuestra base de datos");
    }

    public Map<String, String> jsonParser(String response){
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("response", response);
        return jsonResponse;
    }

    public List<Videogame> getVideogames(){
        return videogameRepository.findAll();
    }

    public List<Videogame> generateRandomGames(String console){
        Random random = new Random();
        List<Videogame> consoleVideogames = videogameRepository.findByVideoConsole(console);
        return Arrays.asList(consoleVideogames.get(random.nextInt(consoleVideogames.size()-1)), consoleVideogames.get(random.nextInt(consoleVideogames.size()-1)));
    }
    public Map<String, List<String>> findRandomVideogames(String console) {
        return (!videogameRepository.findByVideoConsole(console).isEmpty()) ?
                jsonListParser(generateRandomVideogames(console)) :
                jsonListParser(Arrays.asList("No existe consola o está mal escrita"));
    }

    public List<String> generateRandomVideogames (String console){
        List<Videogame> randomGames = generateRandomGames(console);
        List<String> randomGamesAsString = new ArrayList<>();
        randomGamesAsString.add(randomGames.get(0).getName() + " - " + randomGames.get(0).getVideoConsole() + " - " + String.join(", ", randomGames.get(0).getGenres()));
        randomGamesAsString.add(randomGames.get(1).getName() + " - " + randomGames.get(1).getVideoConsole() + " - " + String.join(", ", randomGames.get(1).getGenres()));
        return randomGamesAsString;
    }

    public Map<String, List<String>> jsonListParser(List <String> randomGamesAsString){
        Map<String, List<String>> response = new HashMap<>();
        response.put("response", randomGamesAsString);
        return response;
    }


}
