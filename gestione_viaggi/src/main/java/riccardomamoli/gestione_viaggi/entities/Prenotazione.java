package riccardomamoli.gestione_viaggi.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idDipendente")
    private Dipendente dipendente;

    @OneToOne
    @JoinColumn(name = "idViaggio")
    private Viaggio viaggio;

    private LocalDate dataRichiesta;

    private String preferenzeDipendente;


    public Prenotazione(){}

    public Prenotazione(Dipendente dipendente, Viaggio viaggio, LocalDate dataRichiesta, String preferenzeDipendente) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.dataRichiesta = dataRichiesta;
        this.preferenzeDipendente = preferenzeDipendente;
    }

    public Long getId() {
        return id;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getPreferenzeDipendente() {
        return preferenzeDipendente;
    }

    public void setPreferenzeDipendente(String preferenzeDipendente) {
        this.preferenzeDipendente = preferenzeDipendente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dipendente=" + dipendente +
                ", viaggio=" + viaggio +
                ", dataRichiesta=" + dataRichiesta +
                ", preferenzeDipendente='" + preferenzeDipendente + '\'' +
                '}';
    }
}
