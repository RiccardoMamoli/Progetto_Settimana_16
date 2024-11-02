package riccardomamoli.gestione_viaggi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardomamoli.gestione_viaggi.entities.Dipendente;
import riccardomamoli.gestione_viaggi.entities.Prenotazione;
import riccardomamoli.gestione_viaggi.entities.Viaggio;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.exceptions.NotFoundException;
import riccardomamoli.gestione_viaggi.payloads.NewPrenotazioneDTO;
import riccardomamoli.gestione_viaggi.repositories.DipendenteRepository;
import riccardomamoli.gestione_viaggi.repositories.PrenotazioneRepository;
import riccardomamoli.gestione_viaggi.repositories.ViaggioRepository;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Prenotazione findById(Long idPrenotazione) {
        return prenotazioneRepository.findById(idPrenotazione).orElseThrow(() -> new NotFoundException(idPrenotazione));
    }

    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione savePrenotazione(NewPrenotazioneDTO body) {
        Viaggio viaggio = viaggioRepository.findById(body.idViaggio()).orElseThrow(() -> new NotFoundException(body.idViaggio()));
        Dipendente dipendente = dipendenteRepository.findById(body.idDipendente()).orElseThrow(() -> new NotFoundException(body.idDipendente()));

        if(prenotazioneRepository.existsByIdDipendenteIdAndDataRichiesta(dipendente.getIdDipendente(), body.dataRichiesta())) {
            throw new BadRequestException("Esiste gia una prenotazione per il dipendente con ID " + dipendente.getIdDipendente() + " in data " + body.dataRichiesta());
        }

        Prenotazione prenotazione = new Prenotazione(dipendente, viaggio, body.dataRichiesta(), body.preferenzeDipendente());
       return this.prenotazioneRepository.save(prenotazione);
    }

    public void findByIdAndDelete(Long id) {
        Prenotazione prenotazione = this.findById(id);
        prenotazioneRepository.delete(prenotazione);
    }


}
