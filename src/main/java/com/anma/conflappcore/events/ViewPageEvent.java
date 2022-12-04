package com.anma.conflappcore.events;

import org.springframework.context.ApplicationEvent;

public class ViewPageEvent extends ApplicationEvent {
    private String message;

    public ViewPageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
