package riccardomamoli.gestione_viaggi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardomamoli.gestione_viaggi.repositories.ViaggioRepository;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;
}
