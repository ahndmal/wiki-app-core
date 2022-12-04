package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepo extends JpaRepository<Page, Long> {

    @Query(value = "SELECT p FROM Page p WHERE p.title = ?1 AND p.spaceKey = ?2")
    Page findByTitleAndSpacekey(String pageTitle, String spaceKey);

    @Query(value = "SELECT * FROM pages p WHERE p.title = ?1", nativeQuery = true)
    Page findByTitle(String pageTitle);

    @Query(value = "select * from pages p where p.las", nativeQuery = true)
    List<Page> getLastUpdated();
}
