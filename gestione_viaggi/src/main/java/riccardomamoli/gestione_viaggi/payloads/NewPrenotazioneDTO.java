package riccardomamoli.gestione_viaggi.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewPrenotazioneDTO(
        @NotNull(message = "Inserire un ID dipendente!")
        Long idDipendente,

        @NotNull(message = "Inserire un ID viaggio!")
        Long idViaggio,

        @NotNull(message = "La data è obbligatoria")
        @Future(message = "La data deve essere futura")
        LocalDate dataRichiesta,

        @NotEmpty(message = "Le annotazioni sono obbligatorie")
        @Size(min = 3, max = 500, message = "Le annotazioni devono essere comprese tra 5 e 200 caratteri")
        String preferenzeDipendente
) {

}
