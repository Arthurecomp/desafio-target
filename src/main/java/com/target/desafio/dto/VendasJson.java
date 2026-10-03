package com.target.desafio.dto;

import com.target.desafio.entity.Venda;

import java.util.List;

public record VendasJson(
        List<Venda> vendas
) {
}