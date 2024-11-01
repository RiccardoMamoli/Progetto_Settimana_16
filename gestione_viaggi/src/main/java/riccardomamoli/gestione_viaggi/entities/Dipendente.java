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
    private Long idDipendente;
    private String nomeDipendente;
    private String cognomeDipendente;
    private String usernameDipendente;
    private String emailDipendente;

    public Dipendente(){};

    public Dipendente(String nomeDipendente, String cognomeDipendente, String usernameDipendente, String emailDipendente) {
        this.nomeDipendente = nomeDipendente;
        this.cognomeDipendente = cognomeDipendente;
        this.usernameDipendente = usernameDipendente;
        this.emailDipendente = emailDipendente;
    }

    public Long getIdDipendente() {
        return idDipendente;
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
                "idDipendente=" + idDipendente +
                ", nomeDipendente='" + nomeDipendente + '\'' +
                ", cognomeDipendente='" + cognomeDipendente + '\'' +
                ", usernameDipendente='" + usernameDipendente + '\'' +
                ", emailDipendente='" + emailDipendente + '\'' +
                '}';
    }
}
