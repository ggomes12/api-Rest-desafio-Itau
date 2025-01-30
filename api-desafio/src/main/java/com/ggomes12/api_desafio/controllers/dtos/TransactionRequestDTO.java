package com.ggomes12.api_desafio.controllers.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dataHora) {

}
