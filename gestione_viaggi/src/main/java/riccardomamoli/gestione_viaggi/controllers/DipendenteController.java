package riccardomamoli.gestione_viaggi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riccardomamoli.gestione_viaggi.entities.Dipendente;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.payloads.NewDipendenteDTO;
import riccardomamoli.gestione_viaggi.services.DipendenteService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendenteService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.dipendenteService.saveDipendente(body);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable Long dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(
            @PathVariable Long dipendenteId,
            @RequestBody @Validated NewDipendenteDTO body,
            BindingResult validationResult) {
        if (validationResult.hasErrors()) {String message = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long dipendenteId) {
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }

    @PatchMapping("/{dipendenteId}/upload")
    public String uploadFotoProfilo(
            @PathVariable Long dipendenteId,
            @RequestParam("foto") MultipartFile file) {
        return this.dipendenteService.uploadFotoProfilo(file, dipendenteId);
    }

}
