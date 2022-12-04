package com.anma.conflappcore.grpc;


import com.anma.conflappcore.repo.PageRepo;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import io.grpc.protobuf.services.ProtoReflectionService;


@GRpcService
public class PageGRService extends PageServiceGrpc.PageServiceImplBase  { //extends com.anma.grpc.PageServiceGrpc

    private final PageRepo pageRepo;

    public PageGRService(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @Override
    public void getPage(PageRequest request, StreamObserver<PageResponse> responseObserver) {
        var page = pageRepo.getById(request.getId());
        PageResponse pageResp = PageResponse.newBuilder()
                .setBody(page.getBody())
                .setId(page.getId())
                .setTitle(page.getTitle())
                .setParentId(page.getParentId())
                .setSpaceKey(page.getSpaceKey())
                .setAuthorId(page.getAuthorId())
                .build();
        responseObserver.onNext(pageResp);
        responseObserver.onCompleted();
    }

    @Override
    public void getPages(PageRequest request, StreamObserver<PageResponse> responseObserver) {


    }
}
