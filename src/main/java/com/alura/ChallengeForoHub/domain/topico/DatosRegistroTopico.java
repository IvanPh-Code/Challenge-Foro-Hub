package com.alura.ChallengeForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotBlank(message = "Título es obligatorio")
        String titulo,
        @NotBlank(message = "Mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "el id del autor es obligatorio")
        Long autor,
        @NotNull(message = "el id del Curso es obligatorio")
        Long curso

) {
}
