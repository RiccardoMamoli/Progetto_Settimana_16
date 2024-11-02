package riccardomamoli.gestione_viaggi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardomamoli.gestione_viaggi.entities.Prenotazione;
import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
 boolean exsistsByIdPrenotazione(Long idPrenotazione);

 boolean existsByIdDipendenteIdAndDataRichiesta(Long dipendenteId, LocalDate data);

 Page<Prenotazione> findByIdDipendente(Long idDipendente, Pageable pageable);
}
