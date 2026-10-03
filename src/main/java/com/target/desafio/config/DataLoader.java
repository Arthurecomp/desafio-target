package com.target.desafio.config;


import com.target.desafio.dto.EstoqueJson;
import com.target.desafio.dto.VendasJson;
import com.target.desafio.repository.ProdutoRepository;
import com.target.desafio.repository.VendaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(
            VendaRepository vendaRepository,
            ProdutoRepository produtoRepository,
            ObjectMapper objectMapper
    ) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        ClassPathResource vendasResource =
                new ClassPathResource("vendas.json");

        VendasJson vendasJson = objectMapper.readValue(
                vendasResource.getInputStream(),
                VendasJson.class
        );

        vendaRepository.saveAll(vendasJson.vendas());


        ClassPathResource estoqueResource =
                new ClassPathResource("estoque.json");

        EstoqueJson estoqueJson = objectMapper.readValue(
                estoqueResource.getInputStream(),
                EstoqueJson.class
        );

        produtoRepository.saveAll(estoqueJson.estoque());
    }
}