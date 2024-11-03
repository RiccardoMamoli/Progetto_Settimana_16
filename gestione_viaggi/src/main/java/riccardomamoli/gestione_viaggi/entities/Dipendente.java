package riccardomamoli.gestione_viaggi.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dipendenti")

public class Dipendente {

    @Id
    @GeneratedValue
    private Long id;
    private String nomeDipendente;
    private String cognomeDipendente;
    private String usernameDipendente;
    private String emailDipendente;
    private String immagineProfilo;

    public Dipendente(){};

    public Dipendente(String nomeDipendente, String cognomeDipendente, String usernameDipendente, String emailDipendente, String immagineProfilo) {
        this.nomeDipendente = nomeDipendente;
        this.cognomeDipendente = cognomeDipendente;
        this.usernameDipendente = usernameDipendente;
        this.emailDipendente = emailDipendente;
        this.immagineProfilo = immagineProfilo;
    }

    public String getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setImmagineProfilo(String immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
    }

    public Long getId() {
        return id;
    }

    public String getNomeDipendente() {
        return nomeDipendente;
    }

    public void setNomeDipendente(String nomeDipendente) {
        this.nomeDipendente = nomeDipendente;
    }

    public String getCognomeDipendente() {
        return cognomeDipendente;
    }

    public void setCognomeDipendente(String cognomeDipendente) {
        this.cognomeDipendente = cognomeDipendente;
    }

    public String getUsernameDipendente() {
        return usernameDipendente;
    }

    public void setUsernameDipendente(String usernameDipendente) {
        this.usernameDipendente = usernameDipendente;
    }

    public String getEmailDipendente() {
        return emailDipendente;
    }

    public void setEmailDipendente(String emailDipendente) {
        this.emailDipendente = emailDipendente;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", nomeDipendente='" + nomeDipendente + '\'' +
                ", cognomeDipendente='" + cognomeDipendente + '\'' +
                ", usernameDipendente='" + usernameDipendente + '\'' +
                ", emailDipendente='" + emailDipendente + '\'' +
                ", immagineProfilo='" + immagineProfilo + '\'' +
                '}';
    }
}
