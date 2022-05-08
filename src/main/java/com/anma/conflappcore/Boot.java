package com.anma.conflappcore;


import com.anma.conflappcore.models.db.Comment;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Boot implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Boot.class);
    private final CommentRepo commentRepo;

    @Autowired
    public Boot(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public void run(String... args) throws Exception {
//        loadData();
    }

    private void loadData() {
        int count = 1;
        for (int i = 1; i < 20; i++) {
            var comment = new Comment();
            comment.setId(i);
            comment.setTitle(String.format("Page %d", RandomUtils.getRandomNum(1, 100)));
            comment.setBody(RandomUtils.getRandomText(50));
            comment.setCreatedAt(LocalDateTime.now());
            comment.setLastEdited(LocalDateTime.now());
            comment.setParentId(1);
            comment.setUserId(RandomUtils.getRandomNum(1, 100));
            Comment save = commentRepo.save(comment);
            if (save.getId() != 0) {
                count ++;
                log.info(">> comment created");
            }
        }
        log.info(count + "  Comments created!");
    }
}
