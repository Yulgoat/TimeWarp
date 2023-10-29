package fr.mightycode.cpoo.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.mightycode.cpoo.server.dto.DiscussionDTO;
import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.service.DiscussionService;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/discussions")
@RequiredArgsConstructor
@CrossOrigin
public class DiscussionController {

    private final DiscussionService discussionService;

    /*@GetMapping("/{username}")
    public ResponseEntity<List<Discussion>> getDiscussionsForUser(@PathVariable String username) {
        List<Discussion> discussions = discussionService.getDiscussionsForUser(username);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }*/

    @GetMapping()
    public ResponseEntity<List<Discussion>> getDiscussionsForUser(final Principal user) {
        List<Discussion> discussions = discussionService.getDiscussionsForUser(user.getName());
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @PostMapping(value = "create")
    public ResponseEntity<Discussion> createDiscussion(final Principal user, @RequestBody final String recipient) {
        System.out.println(recipient);
        Discussion discussion = discussionService.createDiscussion(user.getName(), recipient);
        return new ResponseEntity<>(discussion, HttpStatus.CREATED);
    }
}

