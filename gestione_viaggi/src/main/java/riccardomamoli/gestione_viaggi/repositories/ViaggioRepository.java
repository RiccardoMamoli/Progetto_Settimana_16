package riccardomamoli.gestione_viaggi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardomamoli.gestione_viaggi.entities.Viaggio;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
