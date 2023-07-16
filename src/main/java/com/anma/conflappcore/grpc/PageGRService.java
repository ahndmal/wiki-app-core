package com.anma.conflappcore.grpc;

import com.anma.conflappcore.models.db.WikiPage;
import com.anma.conflappcore.repo.PageRepo;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.List;

@GRpcService
public class PageGRService extends PageServiceGrpc.PageServiceImplBase  { //extends com.anma.grpc.PageServiceGrpc
    private final PageRepo pageRepo;

    public PageGRService(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @Override
    public void getPage(PageRequest request, StreamObserver<PageResponse> responseObserver) {
        WikiPage page = pageRepo.findById(request.getId()).get();
        PageResponse pageResp = PageResponse.newBuilder()
                .setBody(page.getBody())
                .setId(page.getId())
                .setTitle(page.getTitle())
                .setParentId(page.getParentId())
                .setSpaceKey("")
                .setAuthorId(page.getAuthorId())
                .build();
        responseObserver.onNext(pageResp);
        responseObserver.onCompleted();
    }

    @Override
    public void getPages(PagesRequest request, StreamObserver<PageResponse> responseObserver) {
        List<WikiPage> pages = pageRepo.findAll();
        for (int i = 0; i <= request.getLimit(); i++) {
            WikiPage page = pageRepo.findById(pages.get(i).getId()).get();
            PageResponse pageResp = PageResponse.newBuilder()
                    .setBody(page.getBody())
                    .setId(page.getId())
                    .setTitle(page.getTitle())
                    .setParentId(page.getParentId())
                    .setSpaceKey("")
                    .setAuthorId(page.getAuthorId())
                    .build();
            responseObserver.onNext(pageResp);
        }
        responseObserver.onCompleted();
    }
}
