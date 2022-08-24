package com.anma.conflappcore.events;

import com.anma.conflappcore.repo.CommentRepo;
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
    Logger log = LoggerFactory.getLogger(CheckDataEvents.class);
    private final CommentRepo commentRepo;
    private final ApplicationEventPublisher publisher;


    public CheckDataEvents(CommentRepo commentRepo, ApplicationEventPublisher publisher) {
        this.commentRepo = commentRepo;
        this.publisher = publisher;
    }

    @Scheduled(fixedRate = 30000L)
    public void scheduleCOmmCheck() {
        int commentsSize = commentRepo.findAll().size();
        System.out.println("comments size is " + commentsSize);
    }

    @EventListener
    public void handle(ContextStartedEvent event) {
        Object source = event.getSource();
        long timestamp = event.getTimestamp();
        ApplicationContext applicationContext = event.getApplicationContext();

    }

    @EventListener(classes = {ContextStartedEvent.class, ContextRefreshedEvent.class})
    public void handleStartEvents() {
        log.info("ContextStartedEvent or ContextRefreshedEvent annotation based listener raised");
    }
}
