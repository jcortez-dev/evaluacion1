package cl.ufro.dci.evaluacion1.repositories;

import cl.ufro.dci.evaluacion1.models.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
    Optional<Videogame> findByName(String name);
}
