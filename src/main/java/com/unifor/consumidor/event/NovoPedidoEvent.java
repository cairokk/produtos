package com.unifor.consumidor.event;

import com.unifor.produtos.DTO.ProdutoResumidoTO;

import java.util.List;

public class NovoPedidoEvent {

    Long pedidoId;
    List<ProdutoResumidoTO> items;

    public NovoPedidoEvent() { };

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public List<ProdutoResumidoTO> getItems() {
        return items;
    }

    public void setItems(List<ProdutoResumidoTO> items) {
        this.items = items;
    }
}
