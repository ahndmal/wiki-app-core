package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comments c where c.parent_id = ?1", nativeQuery = true)
    List<Comment> findCommentByParentId(long parentId);
}
