package fr.mightycode.cpoo.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.repository.DiscussionRepository;
import lombok.RequiredArgsConstructor;

/**
 * Service for managing discussions and their interactions.
 */
@Service
@RequiredArgsConstructor
public class DiscussionService {
    
    private final DiscussionRepository discussionRepository;

    /**
     * Create a new discussion between two users or retrieve an existing one if it already exists.
     *
     * @param user1 The username of the first user.
     * @param user2 The username of the second user.
     * @return The created or existing discussion.
     */
    public Discussion createDiscussion(String user1, String user2) {
        List<Discussion> existingDiscussions1 = discussionRepository.findDiscussionsByUser1AndUser2(user1, user2);
        List<Discussion> existingDiscussions2 = discussionRepository.findDiscussionsByUser1AndUser2(user2, user1);
        if (!existingDiscussions1.isEmpty()) {
            return existingDiscussions1.get(0);
        } else if (!existingDiscussions2.isEmpty()) {
            return existingDiscussions2.get(0);
        } else {
            Discussion discussion = new Discussion();
            discussion.setId(UUID.randomUUID());
            discussion.setUser1(user1);
            discussion.setUser2(user2);
            return discussionRepository.save(discussion);
        }
    }

    /**
     * Get a list of discussions for a user.
     *
     * @param username The username of the user for whom discussions are fetched.
     * @return A list of discussions associated with the user.
     */
    public List<Discussion> getDiscussionsForUser(String username) {
        return discussionRepository.findDiscussionsByUser1OrUser2(username, username);
    }

    /**
     * Get a discussion by its unique identifier (UUID).
     *
     * @param id The UUID of the discussion to retrieve.
     * @return The discussion with the specified UUID, or null if not found.
     */
    public Discussion getDiscussionById(UUID id) {
        return discussionRepository.findById(id).orElse(null);
    }
}
