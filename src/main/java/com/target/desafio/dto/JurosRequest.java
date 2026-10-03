package com.target.desafio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JurosRequest(
        BigDecimal valor,
        LocalDate dataVencimento
) {
}