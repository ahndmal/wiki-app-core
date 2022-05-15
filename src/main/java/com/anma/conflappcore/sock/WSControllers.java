package com.anma.conflappcore.sock;

import com.anma.conflappcore.rest.dto.CommentDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSControllers {

    @MessageMapping("/comments")
    @SendTo("/topic/common")
    public CommentDTO sendComment(CommentDTO dto) {
        try {
            Thread.sleep(100);
            return new CommentDTO(dto.id(), dto.body(), dto.createdAt(), dto.authorId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
