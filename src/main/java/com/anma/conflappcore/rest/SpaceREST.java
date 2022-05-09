package com.anma.conflappcore.rest;

import com.anma.conflappcore.models.db.Space;
import com.anma.conflappcore.repo.SpaceRepo;
import com.anma.conflappcore.rest.dto.SpaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rest/api/spaces")
public class SpaceREST {

    @Autowired
    private final SpaceRepo spaceRepo;

    public SpaceREST(SpaceRepo spaceRepo) {
        this.spaceRepo = spaceRepo;
    }

    @GetMapping()
    public List<Space> getSpaces() {
        return spaceRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public Space getSpaceId(@PathVariable long id) {
        return spaceRepo.getById(id);
    }

    @GetMapping("/key/{key}")
    public Space getSpaceByKey(@PathVariable String key) {
        return spaceRepo.findBySpaceKey(key);
    }

    @PostMapping
    public Space createSpace(SpaceDTO dto) {
        var space = new Space();
        space.setTitle(dto.title());
        space.setSpaceKey(dto.category());
        space.setCategory(dto.category());
        return spaceRepo.save(space);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SpaceDTO saveSpace(@PathVariable long id, @RequestBody CreateSpaceReq dto) {
        var edited = spaceRepo.getById(id);
        edited.setTitle(dto.title());
        edited.setSpaceKey(dto.key());
        edited.setCategory(dto.category());
        edited.setAuthorId(1);  // todo - auth user
        edited.setLastUpdated(LocalDateTime.now());
        edited.setCreatedAt(LocalDateTime.now());
        var savedSpace = spaceRepo.save(edited);
        return new SpaceDTO(
                savedSpace.getId(),
                savedSpace.getTitle(),
                savedSpace.getSpaceKey(),
                savedSpace.getCategory(),
                savedSpace.getAuthorId()
        );
    }
}
