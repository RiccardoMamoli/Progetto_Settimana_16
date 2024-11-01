package riccardomamoli.gestione_viaggi.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri!")
        String nomeDipendente,

        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 3, max=30, message = "Il cognome deve essere compreso tra 3 e 30 caratteri!")
        String cognomeDipendente,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "L'email inserita non è un'email valida")
        String emailDipendente,

        @NotEmpty
        @Size(min = 3, max=10, message = "L'username deve essere compreso tra 3 e 10 caratteri!")
        String usernameDipendente

) {
}
