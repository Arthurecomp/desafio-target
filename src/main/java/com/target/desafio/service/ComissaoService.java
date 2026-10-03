package com.target.desafio.service;

import com.target.desafio.entity.Venda;
import com.target.desafio.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComissaoService {

    private final VendaRepository vendaRepository;

    public ComissaoService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Map<String, BigDecimal> calcularComissoes() {

        List<Venda> vendas = vendaRepository.findAll();

        Map<String, BigDecimal> comissoes = new HashMap<>();

        for (Venda venda : vendas) {

            BigDecimal comissao = calcularComissao(venda.getValor());

            String vendedor = venda.getVendedor();

            if (comissoes.containsKey(vendedor)) {

                BigDecimal valorAtual = comissoes.get(vendedor);

                comissoes.put(vendedor, valorAtual.add(comissao));

            } else {

                comissoes.put(vendedor, comissao);
            }
        }

        return comissoes;
    }

    private BigDecimal calcularComissao(BigDecimal valor) {

        if (valor.compareTo(new BigDecimal("100.00")) < 0) {
            return BigDecimal.ZERO;
        }

        if (valor.compareTo(new BigDecimal("500.00")) < 0) {
            return valor.multiply(new BigDecimal("0.01"));
        }

        return valor.multiply(new BigDecimal("0.05"));
    }
}