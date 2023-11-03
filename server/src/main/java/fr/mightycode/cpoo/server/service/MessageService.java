package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  private final MessageRepository messageRepository;
  private final DiscussionService discussionService;

  // All incoming messages from the router are queued into per recipient queues
  private final Map<String, BlockingQueue<Message>> messageQueues = new HashMap<>();

  /**
   * Get the message queue of the recipient (create it if it does not exist).
   *
   * @param to The recipient address
   * @return the message queue of the recipient
   */
  private BlockingQueue<Message> getQueue(String to) {
    BlockingQueue<Message> messageQueue = messageQueues.get(to);
    if (messageQueue == null) {
      messageQueue = new ArrayBlockingQueue<>(10);
      messageQueues.put(to, messageQueue);
    }
    return messageQueue;
  }

  /**
   * Store a message in the database.
   *
   * @param message The message to store
   * @return the stored message
   */
  public Message storeMessage(Message message) {
    return messageRepository.save(message);
  }

  /**
   * Queue a message into a the message queue of a recipient.
   *
   * @param message The message to queue
   */
  public void queueMessage(Message message) {
    log.info("Queueing message {}", message);
    getQueue(message.getTo()).add(message);
  }

  /**
   * Get all messages sent in a discussion.
   *
   * @param discussionId The discussion uuid
   * @return the list of messages sent to or by the user
   */
  public List<Message> getMessages(UUID discussionId) {
    Discussion discussion = discussionService.getDiscussionById(discussionId);
    String user1 = discussion.getUser1();
    String user2 = discussion.getUser2();
    //return messageRepository.findByFromOrToIgnoreCaseOrderByTimestampAsc(user1, user2);
    return messageRepository.findMessagesBetweenUsers(user1, user2);
  }

  /**
   * Get the next message sent to a given user.
   * This call blocks until an incoming message is received for the user or until a timeout expires.
   *
   * @param to The user address
   * @return the message
   */
  public Message getNextMessage(String to) throws InterruptedException {
    return getQueue(to).poll(5, TimeUnit.SECONDS);
  }
}
