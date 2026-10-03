package com.target.desafio.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    private Long codigoProduto;

    private String descricaoProduto;

    private Integer estoque;

    public Produto() {
    }

    public Produto(Long codigoProduto, String descricaoProduto, Integer estoque) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.estoque = estoque;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}