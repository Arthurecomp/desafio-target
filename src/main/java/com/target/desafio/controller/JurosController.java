package com.target.desafio.controller;

import com.target.desafio.dto.JurosRequest;
import com.target.desafio.service.JurosService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/juros")
public class JurosController {

    private final JurosService jurosService;

    public JurosController(JurosService jurosService) {
        this.jurosService = jurosService;
    }

    @PostMapping("/calcular")
    public BigDecimal calcularJuros(@RequestBody JurosRequest request ) {

        return jurosService.calcularJuros(
                request.valor(),
                request.dataVencimento()
        );
    }
}