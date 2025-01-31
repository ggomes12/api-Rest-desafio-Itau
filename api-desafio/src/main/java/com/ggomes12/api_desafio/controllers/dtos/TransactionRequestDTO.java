package com.ggomes12.api_desafio.controllers.dtos;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequestDTO(@NotNull @Positive Double valor, @NotNull OffsetDateTime dataHora) {

}
