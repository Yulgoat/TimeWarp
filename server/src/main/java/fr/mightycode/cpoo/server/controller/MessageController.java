package fr.mightycode.cpoo.server.controller;

import fr.mightycode.cpoo.server.dto.PostMessageDTO;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.service.MessageService;
import fr.mightycode.cpoo.server.service.RouterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("discussions/message")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  private final RouterService routerService;

  private final MessageService messageService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> messagePost(final Principal user, @RequestBody final PostMessageDTO postMessage) {

    // Build a router message from the DTO
    RouterService.Message routerMessage = new RouterService.Message(
      UUID.randomUUID(),
      System.currentTimeMillis(),
      user.getName() + "@" + serverDomain,
      postMessage.to(),
      postMessage.type(),
      postMessage.body()
    );

    // Build a model message from the router message
    Message message = new Message(routerMessage);


    // Route the message
    routerService.routeMessage(routerMessage);

    // Store the message only if the recipient is on another domain
    // (otherwise, the message will be routed back to the server, and will be stored at that time)
    if (!routerMessage.to().contains("@" + serverDomain))
      messageService.storeMessage(message);

    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<Message>> messageGet(final Principal user) {
    DeferredResult<ResponseEntity<Message>> deferredResult = new DeferredResult<>();
    try {
      Message message = messageService.getNextMessage(user.getName() + "@" + serverDomain);
      if (message == null)
        deferredResult.setResult(new ResponseEntity<>(HttpStatus.ACCEPTED));
      else
        deferredResult.setResult(new ResponseEntity<>(message, HttpStatus.OK));
      return deferredResult;
    }
    catch (final InterruptedException ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }
}
