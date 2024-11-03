package riccardomamoli.gestione_viaggi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import riccardomamoli.gestione_viaggi.entities.Dipendente;
import riccardomamoli.gestione_viaggi.exceptions.BadRequestException;
import riccardomamoli.gestione_viaggi.exceptions.NotFoundException;
import riccardomamoli.gestione_viaggi.payloads.NewDipendenteDTO;
import riccardomamoli.gestione_viaggi.repositories.DipendenteRepository;

import java.io.IOException;


@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;


    public Dipendente saveDipendente (NewDipendenteDTO body) {
        this.dipendenteRepository.findByEmailDipendente(body.emailDipendente()).ifPresent(dipendente -> {
            throw new BadRequestException("Email " + body.emailDipendente() + " gia in uso!");
        });

        this.dipendenteRepository.findByUsernameDipendente(body.usernameDipendente()).ifPresent(dipendente -> {
            throw new BadRequestException("Username " + body.usernameDipendente() + " gia in uso!");
        });

        Dipendente newDipendente = new Dipendente(body.nomeDipendente(), body.cognomeDipendente(),
                body.usernameDipendente(), body.emailDipendente(),
                "https://ui-avatars.com/api/?name=" + body.nomeDipendente() + "+" + body.cognomeDipendente());

        return this.dipendenteRepository.save(newDipendente);
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }


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

    public String uploadFotoProfilo(MultipartFile file, Long idDipendente) {
        try {
            String url = (String) cloudinaryUploader.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url");

            Dipendente found = this.findById(idDipendente);
            found.setImmagineProfilo(url);
            dipendenteRepository.save(found);

            return url;
        } catch (IOException e) {
            throw new BadRequestException("Errore durante l'upload dell'immagine!");
        }
    }


}
