package fr.mightycode.cpoo.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.mightycode.cpoo.server.dto.UnreadMessageDTO;
import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.service.DiscussionService;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/discussions")
@RequiredArgsConstructor
@CrossOrigin
public class DiscussionController {

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  private final DiscussionService discussionService;

    /*@GetMapping("/{username}")
    public ResponseEntity<List<Discussion>> getDiscussionsForUser(@PathVariable String username) {
        List<Discussion> discussions = discussionService.getDiscussionsForUser(username);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }*/

  @GetMapping()
  public ResponseEntity<List<Discussion>> getDiscussionsForUser(final Principal user) {
    List<Discussion> discussions = discussionService.getDiscussionsForUser(user.getName() + "@" + serverDomain);
    return new ResponseEntity<>(discussions, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<Discussion> createDiscussion(final Principal user, @RequestBody final String recipient) {
    Discussion discussion = discussionService.createDiscussion(user.getName() + "@" + serverDomain, recipient);
    return new ResponseEntity<>(discussion, HttpStatus.CREATED);
  }

  @PatchMapping()
  public ResponseEntity<Long> changeTimestampDiscussion(@RequestBody final String discussion_id) {
    UUID uuid = UUID.fromString(discussion_id);
    long newTimestamp = discussionService.changeTimestampDiscussion(uuid);
    return new ResponseEntity<>(newTimestamp, HttpStatus.OK);
  }

  @PatchMapping(value = "unreadmessage", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> changeUnreadMessage(@RequestBody final UnreadMessageDTO unreadMessageDTO) {
    discussionService.updateUnreadMessage(unreadMessageDTO.discussionId(), unreadMessageDTO.unreadMessage());
    return ResponseEntity.ok().build();
  }
}

