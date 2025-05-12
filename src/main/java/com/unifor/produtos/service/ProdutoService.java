package com.unifor.produtos.service;


import com.unifor.produtos.DTO.ProdutoTO;
import com.unifor.produtos.model.Produto;
import com.unifor.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para adicionar novo produto usando ProdutoTO
    public ProdutoTO adicionarProduto(ProdutoTO produtoTO) {
        Produto produto = toEntity(produtoTO);
        Produto produtoSalvo = produtoRepository.save(produto);
        return toTO(produtoSalvo);
    }

    // Listar todos os produtos como ProdutoTO
    public List<ProdutoTO> listarProdutos() {
        return produtoRepository.findAll().stream()
                .map(this::toTO)
                .collect(Collectors.toList());
    }

    // Buscar produto por id e retornar ProdutoTO
    public ProdutoTO buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .map(this::toTO)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    // Atualizar produto existente usando ProdutoTO
    public ProdutoTO alterarProduto(Long id, ProdutoTO produtoTO) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            produtoExistente.setName(produtoTO.getName());
            produtoExistente.setOriginalPrice(produtoTO.getOriginalPrice());
            produtoExistente.setDiscountedPrice(produtoTO.getDiscountedPrice());
            produtoExistente.setCategory(produtoTO.getCategory());
            produtoExistente.setOrigin(produtoTO.getOrigin());
            produtoExistente.setIntensity(produtoTO.getIntensity());
            produtoExistente.setBeans(produtoTO.getBeans());

            Produto produtoAtualizado = produtoRepository.save(produtoExistente);
            return toTO(produtoAtualizado);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    // Remover produto por id
    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // Métodos auxiliares de conversão
    private ProdutoTO toTO(Produto produto) {
        ProdutoTO to = new ProdutoTO();
        to.setId(produto.getId());
        to.setName(produto.getName());
        to.setOriginalPrice(produto.getOriginalPrice());
        to.setDiscountedPrice(produto.getDiscountedPrice());
        to.setCategory(produto.getCategory());
        to.setOrigin(produto.getOrigin());
        to.setIntensity(produto.getIntensity());
        to.setBeans(produto.getBeans());
        return to;
    }

    private Produto toEntity(ProdutoTO produtoTO) {
        Produto produto = new Produto();
        produto.setName(produtoTO.getName());
        produto.setOriginalPrice(produtoTO.getOriginalPrice());
        produto.setDiscountedPrice(produtoTO.getDiscountedPrice());
        produto.setCategory(produtoTO.getCategory());
        produto.setOrigin(produtoTO.getOrigin());
        produto.setIntensity(produtoTO.getIntensity());
        produto.setBeans(produtoTO.getBeans());
        return produto;
    }
}
