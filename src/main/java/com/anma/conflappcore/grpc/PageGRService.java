package com.anma.conflappcore.grpc;


import com.anma.conflappcore.repo.PageRepo;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class PageGRService extends PageServiceGrpc.PageServiceImplBase  { //extends com.anma.grpc.PageServiceGrpc

    private final PageRepo pageRepo;

    public PageGRService(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @Override
    public void getPage(PageRequest request, StreamObserver<PageResponse> responseObserver) {
        PageResponse page = PageResponse.newBuilder()
                .setBody("aaa")
                .setId(1L)
                .build();
        responseObserver.onNext(page);
        responseObserver.onCompleted();
    }

    @Override
    public void getPages(PageRequest request, StreamObserver<PageResponse> responseObserver) {


    }
}
