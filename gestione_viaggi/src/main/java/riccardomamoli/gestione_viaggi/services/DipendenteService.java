package riccardomamoli.gestione_viaggi.services;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardomamoli.gestione_viaggi.entities.Dipendente;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.exceptions.NotFoundException;
import riccardomamoli.gestione_viaggi.payloads.NewDipendenteDTO;
import riccardomamoli.gestione_viaggi.repositories.DipendenteRepository;

import java.awt.print.Pageable;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;


    public Dipendente saveDipendente (NewDipendenteDTO body) {
        this.dipendenteRepository.findByEmailDipendente(body.emailDipendente()).ifPresent(dipendente -> {
            throw new BadRequestException("Email " + body.emailDipendente() + " gia in uso!");
        });

        Dipendente newDipendente = new Dipendente(body.nomeDipendente(), body.cognomeDipendente(), body.emailDipendente(), body.usernameDipendente());

        return this.dipendenteRepository.save(newDipendente);
    }

    /*

    public Page<Dipendente> findAllDipendenti(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }

     */

    public Dipendente findById(Long idDipendente){
        return this.dipendenteRepository.findById(idDipendente).orElseThrow(() -> new NotFoundException(idDipendente));
    }

    public Dipendente findByIdAndUpdate(Long idDipendente, NewDipendenteDTO body) {
        Dipendente found = this.findById(idDipendente);
        if(!found.getEmailDipendente().equals(body.emailDipendente())) {
            this.dipendenteRepository.findByEmailDipendente(body.emailDipendente()).ifPresent(dipendente -> {
                throw new BadRequestException("Email " + body.emailDipendente() + " gia in uso");
            });
        }


            found.setNomeDipendente(body.nomeDipendente());
            found.setCognomeDipendente(body.cognomeDipendente());
            found.setEmailDipendente(body.emailDipendente());
            found.setUsernameDipendente(body.usernameDipendente());

            return this.dipendenteRepository.save(found);
        }


        public void findByIdAndDelete(Long idDipendente) {
        Dipendente found = this.findById(idDipendente);
        this.dipendenteRepository.delete(found);
        }


}
