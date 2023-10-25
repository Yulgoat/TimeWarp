package fr.mightycode.cpoo.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.mightycode.cpoo.server.dto.DiscussionDTO;
import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.service.DiscussionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/discussions")
@RequiredArgsConstructor
public class DiscussionController {

    private final DiscussionService discussionService;

    @GetMapping("value = /{username}")
    public ResponseEntity<List<Discussion>> getDiscussionsForUser(@PathVariable String username) {
        List<Discussion> discussions = discussionService.getDiscussionsForUser(username);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Discussion> createDiscussion(@RequestBody final DiscussionDTO discussionDTO) {
        Discussion discussion = discussionService.createDiscussion(discussionDTO.user1(), discussionDTO.user2());
        return new ResponseEntity<>(discussion, HttpStatus.CREATED);
    }
}

