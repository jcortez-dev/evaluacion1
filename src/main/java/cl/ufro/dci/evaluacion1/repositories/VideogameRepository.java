package cl.ufro.dci.evaluacion1.repositories;

import cl.ufro.dci.evaluacion1.models.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
    @Query("SELECT v FROM Videogame v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<Videogame> findByName(String name);

    List<Videogame> findByVideoConsole(String console);
}
