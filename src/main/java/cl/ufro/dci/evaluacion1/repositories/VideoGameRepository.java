package cl.ufro.dci.evaluacion1.repositories;

import cl.ufro.dci.evaluacion1.models.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * This repository handles the connection to the database
 */
public interface VideoGameRepository extends JpaRepository<VideoGame, Integer> {

    /**
     * Finds a video game based on its name
     *
     * @param name The name of the video game
     * @return The video game
     */
    @Query("SELECT v FROM VideoGame v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<VideoGame> findByName(String name);

    /**
     * Finds video games based on their video console
     *
     * @param console The name of the video console
     * @return The list of video games
     */
    List<VideoGame> findByVideoConsole(String console);
}
