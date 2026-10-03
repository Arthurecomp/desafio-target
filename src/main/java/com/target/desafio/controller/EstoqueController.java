package com.target.desafio.controller;

import com.target.desafio.dto.MovimentacaoRequest;
import com.target.desafio.service.EstoqueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("/movimentacoes")
    public Integer realizarMovimentacao( @RequestBody MovimentacaoRequest request )
    {
        return estoqueService.realizarMovimentacao(request);
    }
}