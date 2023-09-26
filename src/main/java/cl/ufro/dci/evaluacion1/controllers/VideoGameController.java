package cl.ufro.dci.evaluacion1.controllers;

import cl.ufro.dci.evaluacion1.models.VideoGame;
import cl.ufro.dci.evaluacion1.services.VideoGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * This controller handles the queries related to the VideoGame class
 */
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class VideoGameController {
    private final VideoGameService videoGameService;

    /**
     * This method returns the list with all the video games and their attributes
     *
     * @return List of video games
     */
    @GetMapping("/")
    public List<VideoGame> loadVideogames() {
        return videoGameService.getVideogames();
    }


    /**
     * Finds two random video games based on the video console entered by the user
     *
     * @param consoleAbreviation The abreviation of the video console
     * @return Two random video games for the video console
     */
    @GetMapping("consoles/{console_abreviation}/random_games")
    public Map<String, List<String>> findTwoVideoGames(@PathVariable("console_abreviation") String consoleAbreviation){
        return videoGameService.validateVideoConsole(consoleAbreviation);
    }

    /**
     * Finds a video game based on the name entered by the user
     *
     * @param name The name of the video game
     * @return The video game
     */
    @GetMapping("games")
    public Map<String, String> findByName(@RequestParam String name){
        return videoGameService.searchByName(name);
    }
}
