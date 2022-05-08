package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
