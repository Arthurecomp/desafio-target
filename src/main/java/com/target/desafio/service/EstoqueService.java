package com.target.desafio.service;


import com.target.desafio.dto.MovimentacaoRequest;
import com.target.desafio.entity.Movimentacao;
import com.target.desafio.entity.Produto;
import com.target.desafio.entity.TipoMovimentacao;
import com.target.desafio.repository.MovimentacaoRepository;
import com.target.desafio.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public EstoqueService(
            ProdutoRepository produtoRepository,
            MovimentacaoRepository movimentacaoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public Integer realizarMovimentacao(MovimentacaoRequest request) {

        Produto produto = produtoRepository
                .findById(request.codigoProduto())
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado")
                );

        if (request.tipo() == TipoMovimentacao.ENTRADA) {

            produto.setEstoque(produto.getEstoque() + request.quantidade() );

        } else {

            if (request.quantidade() > produto.getEstoque()) {
                throw new RuntimeException("Estoque insuficiente");
            }

            produto.setEstoque(
                    produto.getEstoque() - request.quantidade()
            );
        }

        produtoRepository.save(produto);

        Movimentacao movimentacao = new Movimentacao(
                produto,
                request.tipo(),
                request.descricao(),
                request.quantidade()
        );

        movimentacaoRepository.save(movimentacao);

        return produto.getEstoque();
    }
}