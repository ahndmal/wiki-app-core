package com.anma.conflappcore.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EvListen  implements ApplicationListener<PageCreatedEvent> {

    @Override
    public void onApplicationEvent(PageCreatedEvent event) {
        //do stuff
        Object source = event.getSource();
    }
}

