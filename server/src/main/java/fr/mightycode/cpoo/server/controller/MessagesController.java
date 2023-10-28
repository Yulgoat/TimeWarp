package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("discussions")
@RequiredArgsConstructor
@CrossOrigin
public class MessagesController {

  private final MessageService messageService;

  @GetMapping(value = "{discussion_id}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Message> messagesGet(@PathVariable UUID discussion_id) {
    return messageService.getMessages(discussion_id);
  }
}
