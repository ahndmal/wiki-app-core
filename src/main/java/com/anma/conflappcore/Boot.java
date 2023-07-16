package com.anma.conflappcore;

import com.anma.conflappcore.models.db.Comment;
import com.anma.conflappcore.models.db.WikiPage;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class Boot implements CommandLineRunner {
    private final Logger LOG = LoggerFactory.getLogger(Boot.class);
    private final CommentRepo commentRepo;
    private final PageRepo pageRepo;

    @Autowired
    public Boot(CommentRepo commentRepo, PageRepo pageRepo) {
        this.commentRepo = commentRepo;
        this.pageRepo = pageRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // START

        // LOG.info(String.valueOf(pageRepo.findAll().size()));

        List<WikiPage> pages = pageRepo.findAll().stream().filter(p -> p.getId() < 5000L).toList();
        System.out.println(pages.get(0));

//        createPages(1, 100);
//        createComments(621, 1000);

        // set userId
//        commentRepo.findAll().stream().map(c -> {
//            c.setUserId(2);
//            return commentRepo.save(c);
//        });

        // END
    }

    private void createComments(int from, int to) {
        int count = 1;
        for (int i = from; i < to; i++) {
            var comment = new Comment();
            comment.setId(i);
            comment.setTitle(String.format("Page %d", RandomUtils.getRandomNum(1, 100)));
            comment.setBody(RandomUtils.getRandomText(80));
            comment.setCreatedAt(LocalDateTime.now());
            comment.setLastEdited(LocalDateTime.now());
            comment.setParentId(new Random().nextInt(1, 5));
            comment.setUserId(RandomUtils.getRandomNum(1, 3));
            Comment save = commentRepo.save(comment);
            if (save.getId() != 0) {
                count ++;
                LOG.info(">> comment created");
            }
        }
        LOG.info(count + "  Comments created!");
    }

    private void createPages(int from, int to) {
        for (int i = from; i < to; i++) {
            var page = new WikiPage();
            page.setId(i);
            page.setTitle(String.format("Test Page %d", RandomUtils.getRandomNum(1, 100)));
            page.setBody(RandomUtils.getRandomText(50));
            page.setCreatedAt(LocalDateTime.now());
            page.setLastUpdated(LocalDateTime.now());
            page.setSpaceKey("DEV");
            page.setParentId(0);
            page.setAuthorId(RandomUtils.getRandomNum(1, 5));
            var saved = pageRepo.save(page);
            if (saved.getId() != 0) {
                from ++;
                LOG.info(">> page created");
            }
        }
        LOG.info(from + "  pages created!");
    }
}
