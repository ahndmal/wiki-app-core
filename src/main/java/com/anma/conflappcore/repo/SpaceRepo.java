package com.anma.conflappcore.repo;

import com.anma.conflappcore.models.db.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpaceRepo extends JpaRepository<Space, Long> {

    @Query(value = "SELECT * FROM spaces s WHERE s.title = ?1", nativeQuery = true)
    Space findByTitle(String pageTitle);

    Space findByCategory(String category);

    @Query(value = "SELECT * FROM spaces s WHERE s.space_key = ?1", nativeQuery = true)
    Space findBySpaceKey(String key);
}
