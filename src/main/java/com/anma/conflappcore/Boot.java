package com.anma.conflappcore;

import com.anma.conflappcore.models.db.Comment;
import com.anma.conflappcore.models.db.WikiPage;
import com.anma.conflappcore.models.db.WikiSpace;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.repo.SpaceRepo;
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
    private final SpaceRepo spaceRepo;

    @Autowired
    public Boot(CommentRepo commentRepo, PageRepo pageRepo, SpaceRepo spaceRepo) {
        this.commentRepo = commentRepo;
        this.pageRepo = pageRepo;
        this.spaceRepo = spaceRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // START

//        createPages(1, 100);
//        createComments(621, 1000);

        if (spaceRepo.count() < 10) {
            createSpaces(30);
        }


        // END
    }

    private void createSpaces(int amount) {
        LOG.info(">>> creating spaces");
        for (long i = 4; i <= amount; i++) {
            WikiSpace space = new WikiSpace();
//            space.setId(i);
            space.setSpaceKey("DEV" + i);
            space.setTitle("DEV " + i);
            space.setAuthorId(1L);
            space.setCategory("test");
            space.setCreatedAt(LocalDateTime.now());
            space.setLastUpdated(LocalDateTime.now());
            spaceRepo.save(space);

            LOG.info(">>> created space " + i);

        }
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
                count++;
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
                from++;
                LOG.info(">> page created");
            }
        }
        LOG.info(from + "  pages created!");
    }
}
