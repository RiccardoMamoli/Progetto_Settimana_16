package riccardomamoli.gestione_viaggi.entities;


import jakarta.persistence.*;
import riccardomamoli.gestione_viaggi.enums.StatoViaggio;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue
    private Long id;
    private String destinazioneViaggio;
    private LocalDate dataViaggio;


    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    public Viaggio(){};

    public Viaggio(String destinazioneViaggio, LocalDate dataViaggio, StatoViaggio statoViaggio) {
        this.destinazioneViaggio = destinazioneViaggio;
        this.dataViaggio = dataViaggio;
        this.statoViaggio = statoViaggio;
    }

    public Long getId() {
        return id;
    }


    public String getDestinazioneViaggio() {
        return destinazioneViaggio;
    }

    public void setDestinazioneViaggio(String destinazioneViaggio) {
        this.destinazioneViaggio = destinazioneViaggio;
    }

    public LocalDate getDataViaggio() {
        return dataViaggio;
    }

    public void setDataViaggio(LocalDate dataViaggio) {
        this.dataViaggio = dataViaggio;
    }

    public StatoViaggio getStatoViaggio() {
        return statoViaggio;
    }

    public void setStatoViaggio(StatoViaggio statoViaggio) {
        this.statoViaggio = statoViaggio;
    }

    @Override
    public String toString() {
        return "Viaggio{" +
                "id=" + id +
                ", destinazioneViaggio='" + destinazioneViaggio + '\'' +
                ", dataViaggio=" + dataViaggio +
                ", statoViaggio=" + statoViaggio +
                '}';
    }
}
