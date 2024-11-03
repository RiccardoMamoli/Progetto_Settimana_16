package riccardomamoli.gestione_viaggi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardomamoli.gestione_viaggi.entities.Prenotazione;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.payloads.NewPrenotazioneDTO;
import riccardomamoli.gestione_viaggi.services.PrenotazioneService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<Prenotazione> getAllPrenotazioni(Pageable pageable) {
        return prenotazioneService.getAllPrenotazioni(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.prenotazioneService.savePrenotazione(body);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione getPrenotazioneById(@PathVariable Long prenotazioneId) {
        return prenotazioneService.findById(prenotazioneId);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneService.findByIdAndDelete(prenotazioneId);
    }

    @GetMapping("/data/{dataViaggioRichiesta}")
    public List<Prenotazione> getPrenotazioniByDataRichiesta(@PathVariable LocalDate dataViaggioRichiesta) {
        return prenotazioneService.getPrenotazioniByDataRichiesta(dataViaggioRichiesta);
    }

}
