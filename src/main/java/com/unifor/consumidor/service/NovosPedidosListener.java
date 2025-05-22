package com.unifor.consumidor.service;

import com.unifor.consumidor.event.NovoPedidoEvent;
import com.unifor.produtos.service.ProdutoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NovosPedidosListener {

    @Autowired
    private ProdutoService produtoService;

    @KafkaListener(topics = "atualizar-estoque", groupId = "produto-service-group")
    public void atualizarEstoque(@Payload NovoPedidoEvent evento) {
        evento.getItems().forEach(item -> {
            try {
                produtoService.reduzirEstoque(item.getId(), item.getQuantidade());
            } catch (Exception e) {
                //notificar outros serviços que algo deu errado.
            }
        });
    }

    @KafkaListener(topics = "repor-estoque", groupId = "produto-service-group")
    public void reporEstoque(@Payload NovoPedidoEvent evento) {
        evento.getItems().forEach(item -> {
            try {
                produtoService.reporEstoque(item.getId(), item.getQuantidade());
            } catch (Exception e) {
                //notificar outros serviços que algo deu errado.
            }
        });
    }

}
