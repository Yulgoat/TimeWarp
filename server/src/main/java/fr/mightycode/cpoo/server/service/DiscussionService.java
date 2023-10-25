package fr.mightycode.cpoo.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.repository.DiscussionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscussionService {
    
    private final DiscussionRepository discussionRepository;

    public Discussion createDiscussion(String user1, String user2) {
        List<Discussion> existingDiscussions = discussionRepository.findDiscussionsByUser1AndUser2(user1, user2);
        if (!existingDiscussions.isEmpty()) {
            return existingDiscussions.get(0);
        } else {
            Discussion discussion = new Discussion();
            discussion.setUser1(user1);
            discussion.setUser2(user2);
            return discussionRepository.save(discussion);
        }
    }
    

    public List<Discussion> getDiscussionsForUser(String username) {
        return discussionRepository.findDiscussionsByUser1OrUser2(username, username);
    }

    public Discussion getDiscussionById(UUID id) {
        return discussionRepository.findById(id).orElse(null);
    }

}


