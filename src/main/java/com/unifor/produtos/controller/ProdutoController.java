package com.unifor.produtos.controller;

import com.unifor.produtos.DTO.ProdutoTO;
import com.unifor.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Operações para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Cadastrar novo produto", responses = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ProdutoTO> cadastrarProduto(@RequestBody ProdutoTO produtoTO) {
        ProdutoTO novoProduto = produtoService.adicionarProduto(produtoTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", responses = {
            @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso")
    })
    public ResponseEntity<List<ProdutoTO>> listarProdutos() {
        List<ProdutoTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<ProdutoTO> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoTO produtoTO = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtoTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar produto existente", responses = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ProdutoTO> alterarProduto(@PathVariable Long id, @RequestBody ProdutoTO produtoTO) {
        ProdutoTO produtoAtualizado = produtoService.alterarProduto(id, produtoTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.removerProduto(id);
        return ResponseEntity.noContent().build();
    }
}
