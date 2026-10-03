package com.target.desafio.controller;

import com.target.desafio.service.ComissaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/vendas")
public class ComissaoController {

    private final ComissaoService comissaoService;

    public ComissaoController(ComissaoService comissaoService) {
        this.comissaoService = comissaoService;
    }

    @GetMapping("/comissoes")
    public Map<String, BigDecimal> calcularComissoes() {
        return comissaoService.calcularComissoes();
    }
}