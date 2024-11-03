package riccardomamoli.gestione_viaggi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardomamoli.gestione_viaggi.entities.Dipendente;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmailDipendente(String emailDipendente);
    Optional<Dipendente> findByUsernameDipendente(String usernameDipendente);
}
