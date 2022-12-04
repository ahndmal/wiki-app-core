package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepo extends JpaRepository<Blog, Long> {

    @Query(value = "SELECT * FROM blogs p WHERE p.title = ?1", nativeQuery = true)
    Blog findByTitle(String title);
    Blog findByTitleAndSpaceKey(String title, String spaceKey);

//    @Query(value = "select * from blogs p where p.las", nativeQuery = true)
//    List<Blog> getLastUpdated(Sort sort);
}
