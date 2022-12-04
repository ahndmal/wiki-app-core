package com.anma.conflappcore.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ViewPagePublisher  {
    private static final Logger LOG = LoggerFactory.getLogger(ViewPagePublisher.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    public ViewPagePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishViewPageEvent(final String message) {
        LOG.info("Publishing VIEW_PAGE event. ");
        ViewPageEvent viewPageEvent = new ViewPageEvent(this, message);
        applicationEventPublisher.publishEvent(viewPageEvent);
    }
}
