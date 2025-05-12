package com.unifor.produtos.service;

import br.com.seuapp.grpc.ProdutoIdRequest;
import br.com.seuapp.grpc.ProdutoResponse;
import br.com.seuapp.grpc.ProdutoServiceGrpc;
import com.unifor.produtos.DTO.ProdutoTO;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
public class ProdutoGrpcService extends ProdutoServiceGrpc.ProdutoServiceImplBase {

    @Autowired
    private ProdutoService produtoService;

    @Override
    public void buscarProdutoPorId(ProdutoIdRequest request, StreamObserver<ProdutoResponse> responseObserver) {
        //ProdutoTO produto = produtoService.buscarProdutoPorId(request.getId());

        ProdutoResponse response = ProdutoResponse.newBuilder()
                .setId(1)
                .setName("Mock Test")
                .setOriginalPrice(100.0)
                .setDiscountedPrice(90.0)
                .setCategory("Test")
                .setOrigin("Brasil")
                .setIntensity("Médio")
                .setBeans("Arábica")
                .build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}

