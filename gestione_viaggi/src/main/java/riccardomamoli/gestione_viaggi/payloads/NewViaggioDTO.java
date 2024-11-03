package riccardomamoli.gestione_viaggi.payloads;

import jakarta.validation.constraints.*;
import riccardomamoli.gestione_viaggi.enums.StatoViaggio;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotEmpty(message = "La destinazione è obbligatoria!")
        @Size(min = 3, max=30, message = "La destinazione deve essere compresa tra 3 e 30 caratteri!")
        String destinazioneViaggio,

        @NotNull(message = "La data è obbligatoria!")
        @Future(message = "La data deve essere futura!")
        LocalDate dataViaggio,

        @NotNull(message = "Seleziona lo stato per il viaggio!")
        StatoViaggio statoViaggio
) {
}
