package cl.ufro.dci.evaluacion1.services;

import cl.ufro.dci.evaluacion1.models.VideoGame;
import cl.ufro.dci.evaluacion1.repositories.VideoGameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This service handles the logic of the operations related to the VideoGame class
 */
@Service
@RequiredArgsConstructor
public class VideoGameService {
    private final VideoGameRepository videoGameRepository;

    private final String [] VIDEOGAMES_PATHS = {
            "src\\main\\java\\cl\\ufro\\dci\\evaluacion1\\utils\\GBA.json",
    "src\\main\\java\\cl\\ufro\\dci\\evaluacion1\\utils\\N64.json",
    "src\\main\\java\\cl\\ufro\\dci\\evaluacion1\\utils\\PS2.json"};


    /**
     * Sends each JSON file path into the JSON reader method
     *
     * @throws IOException when the JSON file does not exist
     */
    @PostConstruct
    public void loadVideogames() throws IOException {
        for (String path: VIDEOGAMES_PATHS
             ) {
            readJsonFile(path);
        }
    }

    /**
     * Reads a JSON file and saves each video game into the database
     *
     * @param pathname The path of the JSON file
     * @throws IOException when the JSON file does not exist
     */
    public void readJsonFile(String pathname) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        VideoGame[] videoGames = objectMapper.readValue(new File(pathname), VideoGame[].class);
        videoGameRepository.saveAll(Arrays.asList(videoGames));
    }

    /**
     * Searches a video game by its title/name
     *
     * @param name The name of the video game
     * @return The video game
     */
    public Map<String, String> searchByName(String name){
        return videoGameRepository.findByName(name).isPresent() ?
                jsonParser(videoGameRepository.findByName(name).get().getVideoConsole() + " - " + String.join(" - ", videoGameRepository.findByName(name).get().getGenres())) :
                jsonParser("Juego no encontrado en nuestra base de datos");
    }

    /**
     * Parses a String into a specific JSON response
     *
     * @param response A String to be converted into JSON
     * @return a JSON
     */
    public Map<String, String> jsonParser(String response){
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("response", response);
        return jsonResponse;
    }

    /**
     * List of all the video games in the database
     *
     * @return List of video games
     */
    public List<VideoGame> getVideogames(){
        return videoGameRepository.findAll();
    }

    /**
     * Finds two random video games based on a video console
     *
     * @param videoConsole The video console entered by the user
     * @return Two random video games
     */
    public List<VideoGame> findTwoRandomGames(String videoConsole){
        Random random = new Random();
        List<VideoGame> consoleVideoGames = videoGameRepository.findByVideoConsole(videoConsole);
        return Arrays.asList(consoleVideoGames.get(random.nextInt(consoleVideoGames.size()-1)), consoleVideoGames.get(random.nextInt(consoleVideoGames.size()-1)));
    }

    /**
     * Validates the video console entered by the user
     *
     * @param videoConsole The name of the video console
     * @return Two random video games
     */
    public Map<String, List<String>> validateVideoConsole(String videoConsole) {
        return (!videoGameRepository.findByVideoConsole(videoConsole).isEmpty()) ?
                jsonListParser(videoGamesStringParser(videoConsole)) :
                jsonListParser(Arrays.asList("No existe consola o está mal escrita"));
    }


    /**
     * Parses a list of video games into a String List
     *
     * @param videoConsole The name of the video console
     * @return List of video games parsed into String
     */
    public List<String> videoGamesStringParser(String videoConsole){
        List<VideoGame> randomGames = findTwoRandomGames(videoConsole);
        return Arrays.asList(
                randomGames.get(0).getName() + " - " + randomGames.get(0).getVideoConsole() + " - " + String.join(", ", randomGames.get(0).getGenres()),
                randomGames.get(1).getName() + " - " + randomGames.get(1).getVideoConsole() + " - " + String.join(", ", randomGames.get(1).getGenres())
        );
    }

    /**
     * Parses a String List into a specific JSON response
     *
     * @param responseList A String List
     * @return a JSON
     */
    public Map<String, List<String>> jsonListParser(List <String> responseList){
        Map<String, List<String>> response = new HashMap<>();
        response.put("response", responseList);
        return response;
    }


}
