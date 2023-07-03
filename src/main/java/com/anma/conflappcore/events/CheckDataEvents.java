package com.anma.conflappcore.events;

import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckDataEvents {
    Logger LOG = LoggerFactory.getLogger(CheckDataEvents.class);
    private final CommentRepo commentRepo;
    private final PageRepo pageRepo;
    private final ApplicationEventPublisher publisher;

    public CheckDataEvents(CommentRepo commentRepo, ApplicationEventPublisher publisher, PageRepo pageRepo) {
        this.commentRepo = commentRepo;
        this.publisher = publisher;
        this.pageRepo = pageRepo;
    }

    @Scheduled(fixedRate = 30000L)
    public void scheduleCommCheck() {
        int commentsSize = commentRepo.findAll().size();
        LOG.info("comments size is " + commentsSize);
        LOG.info("pages size is " + pageRepo.findAll().size());
    }

    @EventListener
    public void handle(ContextStartedEvent event) {
        Object source = event.getSource();
        long timestamp = event.getTimestamp();
        ApplicationContext applicationContext = event.getApplicationContext();
        LOG.info(">>> ContextStartedEvent :: " + source);
    }

    @EventListener(classes = {ContextStartedEvent.class, ContextRefreshedEvent.class})
    public void handleStartEvents() {
        LOG.info("ContextStartedEvent or ContextRefreshedEvent annotation based listener raised");
    }

    @EventListener
    public void handleCreatePage(PageCreatedEvent event) {
        Object source = event.getSource();
        LOG.info(">>> PageCreatedEvent :: source: " + source);
    }

    @EventListener
    public void handleViewPage(ViewPageEvent event) {
        Object source = event.getSource();
        LOG.info(">>> ViewPageEvent :: " + event.getMessage());
    }
}
