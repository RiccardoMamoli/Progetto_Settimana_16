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
    private LocalDate dataInizioViaggio;
    private LocalDate dataFineViaggio;


    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    public Viaggio(){};

    public Viaggio(String destinazioneViaggio, LocalDate dataInizioViaggio, LocalDate dataFineViaggio, StatoViaggio statoViaggio) {
        this.destinazioneViaggio = destinazioneViaggio;
        this.dataInizioViaggio = dataInizioViaggio;
        this.dataFineViaggio = dataFineViaggio;
        this.statoViaggio = statoViaggio;
    }

    public Long getIdViaggio() {
        return idViaggio;
    }


    public String getDestinazioneViaggio() {
        return destinazioneViaggio;
    }

    public void setDestinazioneViaggio(String destinazioneViaggio) {
        this.destinazioneViaggio = destinazioneViaggio;
    }

    public LocalDate getDataInizioViaggio() {
        return dataInizioViaggio;
    }

    public void setDataInizioViaggio(LocalDate dataInizioViaggio) {
        this.dataInizioViaggio = dataInizioViaggio;
    }

    public LocalDate getDataFineViaggio() {
        return dataFineViaggio;
    }

    public void setDataFineViaggio(LocalDate dataFineViaggio) {
        this.dataFineViaggio = dataFineViaggio;
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
                "idViaggio=" + idViaggio +
                ", destinazioneViaggio='" + destinazioneViaggio + '\'' +
                ", dataInizioViaggio=" + dataInizioViaggio +
                ", dataFineViaggio=" + dataFineViaggio +
                ", statoViaggio=" + statoViaggio +
                '}';
    }
}
