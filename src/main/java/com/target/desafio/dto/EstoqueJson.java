package com.target.desafio.dto;

import com.target.desafio.entity.Produto;

import java.util.List;

public record EstoqueJson(
        List<Produto> estoque
) {
}