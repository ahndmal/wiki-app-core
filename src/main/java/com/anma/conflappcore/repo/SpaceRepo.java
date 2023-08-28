package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.WikiSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpaceRepo extends JpaRepository<WikiSpace, Long> {
    @Query(value = "SELECT s FROM WikiSpace s WHERE s.title = ?1")
    WikiSpace findByTitle(String pageTitle);

    WikiSpace findByCategory(String category);

    @Query(value = "SELECT * FROM spaces s WHERE s.space_key = ?1", nativeQuery = true)
    WikiSpace findBySpaceKey(String key);

    void deleteBySpaceKey(String key);
}
