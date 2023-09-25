package cl.ufro.dci.evaluacion1.repositories;

import cl.ufro.dci.evaluacion1.models.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
}
