package com.target.desafio.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class JurosService {

    private static final BigDecimal TAXA_DIARIA = new BigDecimal("0.025");

    public BigDecimal calcularJuros( BigDecimal valor, LocalDate dataVencimento ) {

        LocalDate hoje = LocalDate.now();

        if (!hoje.isAfter(dataVencimento)) {
            return BigDecimal.ZERO;
        }

        long diasAtraso = ChronoUnit.DAYS.between(
                dataVencimento,
                hoje
        );

        return valor
                .multiply(TAXA_DIARIA)
                .multiply(BigDecimal.valueOf(diasAtraso));
    }
}