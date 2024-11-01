package riccardomamoli.gestione_viaggi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardomamoli.gestione_viaggi.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
 boolean exsistsByIdPrenotazione(Long idPrenotazione);
}
