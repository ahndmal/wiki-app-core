package com.anma.conflappcore.events;

import org.springframework.context.ApplicationEvent;

public class PageCreatedEvent extends ApplicationEvent {

    public PageCreatedEvent(Object source) {
        super(source);
    }
}
