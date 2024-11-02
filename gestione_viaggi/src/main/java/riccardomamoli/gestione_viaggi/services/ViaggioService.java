package riccardomamoli.gestione_viaggi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardomamoli.gestione_viaggi.entities.Viaggio;
import riccardomamoli.gestione_viaggi.enums.StatoViaggio;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.exceptions.NotFoundException;
import riccardomamoli.gestione_viaggi.payloads.NewViaggioDTO;
import riccardomamoli.gestione_viaggi.repositories.ViaggioRepository;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio findById(Long idViaggio) {
        return viaggioRepository.findById(idViaggio).orElseThrow(() -> new NotFoundException(idViaggio));
    }

    public Viaggio saveViaggio(NewViaggioDTO body){
        Viaggio viaggio = new Viaggio(body.destinazioneViaggio(), body.datInizioViaggio(), body.dataFineViaggio() , body.statoViaggio());
        return this.viaggioRepository.save(viaggio);

    }

    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }

    public void findByIdAndDelete(Long id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }

    public Viaggio findByIdAndUpdate(Long idViaggio, NewViaggioDTO body) {
        Viaggio viaggioFound = this.findById(idViaggio);

        if(body.dataFineViaggio().isBefore(viaggioFound.getDataFineViaggio())) {
            throw new BadRequestException("La data di fine non può essere prima di quella di inizio!");
        }

        if(!body.destinazioneViaggio().equals(viaggioFound.getDestinazioneViaggio())) {
            viaggioFound.setDestinazioneViaggio(body.destinazioneViaggio());
        }

        if (!body.dataFineViaggio().equals(viaggioFound.getDataFineViaggio())) {
            viaggioFound.setDataFineViaggio(body.dataFineViaggio());
        }

        if (!body.datInizioViaggio().equals(viaggioFound.getDataInizioViaggio())) {
            viaggioFound.setDataInizioViaggio(body.datInizioViaggio());
        }

        return viaggioRepository.save(viaggioFound);

    }

    public Viaggio updateStatoViaggio(Long idViaggio, String stato) {
        StatoViaggio statoEnum;

        try {
            statoEnum = StatoViaggio.valueOf(stato.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Stato non valido. Stati ammessi: IN_PROGRAMMA, COMPLETATO");
        }

        Viaggio found = this.findById(idViaggio);
        found.setStatoViaggio(statoEnum);
        return viaggioRepository.save(found);
    }




}
