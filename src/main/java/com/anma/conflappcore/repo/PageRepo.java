package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepo extends JpaRepository<Page, Long> {

//    @Query("select p from Page p where p.title = 1")
    @Query(value = "SELECT * FROM pages p WHERE p.title = ?1", nativeQuery = true)
    Page findByTitle(String pageTitle);
    Page findByTitleAndSpaceKey(String pageTitle, String spaceKey);
    @Query(value = "select * from pages p where p.las", nativeQuery = true)
    List<Page> getLastUpdated();
}
