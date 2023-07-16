package com.anma.conflappcore.sock;

import com.anma.conflappcore.models.dto.CommentDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSControllers {

    @MessageMapping("/comments")
    @SendTo("/topic/common")
    public CommentDto sendComment(CommentDto dto) {
        try {
            Thread.sleep(100);
            return new CommentDto(dto.id(), dto.body(), dto.createdAt(), dto.authorId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
