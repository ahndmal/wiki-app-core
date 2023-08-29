package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.WikiPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepo extends JpaRepository<WikiPage, Long> {
//    @Query(value = "SELECT p FROM Page p WHERE p.title = ?1 AND p.spaceKey = ?2")
    WikiPage findByTitleAndSpaceKey(String pageTitle, String spaceKey);

    @Query(value = "SELECT * FROM pages p WHERE p.title = ?1", nativeQuery = true)
    public List<WikiPage> findByTitle(String pageTitle);

//    @Query(value = "select * from pages p where p.last_updated ", nativeQuery = true)
//    List<WikiPage> findByLastUpdated();

    public List<WikiPage> findBySpaceKey(String key);

    @Query(value = "select * from pages p where p.space_key = ?1", nativeQuery = true)
    public List<WikiPage> findBySpaceKeyQuery(String key);
}