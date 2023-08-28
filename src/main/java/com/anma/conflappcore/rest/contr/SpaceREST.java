package com.anma.conflappcore.rest.contr;

import com.anma.conflappcore.models.db.WikiSpace;
import com.anma.conflappcore.repo.SpaceRepo;
import com.anma.conflappcore.models.dto.SpaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rest/api/space")
public class SpaceREST {
    private final SpaceRepo spaceRepo;

    @Autowired
    public SpaceREST(SpaceRepo spaceRepo) {
        this.spaceRepo = spaceRepo;
    }

    @GetMapping()
    public List<WikiSpace> getSpaces() {
        return spaceRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public WikiSpace getSpaceId(@PathVariable long id) {
        return spaceRepo.getById(id);
    }

    @GetMapping("/key/{key}")
    public WikiSpace getSpaceByKey(@PathVariable String key) {
        return spaceRepo.findBySpaceKey(key);
    }

    @PostMapping
    public WikiSpace createSpace(SpaceDto dto) {
        var space = new WikiSpace();
        space.setTitle(dto.getTitle());
        space.setSpaceKey(dto.getKey());
        space.setCategory(dto.getCategory());
        return spaceRepo.save(space);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SpaceDto saveSpace(@PathVariable long id, @RequestBody CreateSpaceReq dto) {
        var edited = spaceRepo.getById(id);
        edited.setTitle(dto.title());
        edited.setSpaceKey(dto.key());
        edited.setCategory(dto.category());
        edited.setAuthorId(1);  // todo - auth user
        edited.setLastUpdated(LocalDateTime.now());
        edited.setCreatedAt(LocalDateTime.now());
        var savedSpace = spaceRepo.save(edited);
        return new SpaceDto(
                savedSpace.getId(),
                savedSpace.getTitle(),
                savedSpace.getSpaceKey(),
                savedSpace.getCategory(),
                savedSpace.getAuthorId()
        );
    }
    @DeleteMapping("/{key}")
    public ResponseEntity<?> deleteSpace(@PathVariable String key) {
        spaceRepo.deleteBySpaceKey(key);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("%s space deleted", key));
    }
}
