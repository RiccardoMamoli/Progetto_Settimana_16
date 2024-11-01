package riccardomamoli.gestione_viaggi.entities;


import jakarta.persistence.*;
import riccardomamoli.gestione_viaggi.enums.StatoViaggio;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue
    private Long idViaggio;
    private String destinazioneViaggio;
    private LocalDate dataViaggio;
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

}
