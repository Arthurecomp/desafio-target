package com.target.desafio.dto;


import com.target.desafio.entity.TipoMovimentacao;

public record MovimentacaoRequest(
        Long codigoProduto,
        TipoMovimentacao tipo,
        String descricao,
        Integer quantidade
) {
}