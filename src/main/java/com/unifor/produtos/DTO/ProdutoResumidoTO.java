package com.unifor.produtos.DTO;

public class ProdutoResumidoTO {

    Long id;
    int quantidade;

    public ProdutoResumidoTO() { };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
