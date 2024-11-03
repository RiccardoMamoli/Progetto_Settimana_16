package riccardomamoli.gestione_viaggi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import riccardomamoli.gestione_viaggi.entities.Viaggio;
import riccardomamoli.gestione_viaggi.enums.StatoViaggio;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.payloads.NewViaggioDTO;
import riccardomamoli.gestione_viaggi.services.ViaggioService;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")

public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> getViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Payload error: " + message);
        }
        return this.viaggioService.saveViaggio(body);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findByIdViaggio(@PathVariable Long idViaggio) {
        return this.viaggioService.findById(idViaggio);
    }

    @PutMapping("/{viaggioId}/statoviaggio")
    public Viaggio updateStatoViaggio(@PathVariable Long viaggioId, @RequestBody Map<String, String> requestBody){

        String statoString = requestBody.get("statoviaggio");

        StatoViaggio stato;

        try {
            stato = StatoViaggio.valueOf(statoString.toUpperCase());
        } catch(IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stato non valido: " + statoString);

        }

        return this.viaggioService.updateStatoViaggio(viaggioId, stato);
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long viaggioId) {
        this.viaggioService.findByIdAndDelete(viaggioId);
    }



}
