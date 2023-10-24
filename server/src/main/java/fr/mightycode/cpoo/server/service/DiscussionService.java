package fr.mightycode.cpoo.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import fr.mightycode.cpoo.server.model.Discussion;
import fr.mightycode.cpoo.server.repository.DiscussionRepository;

public class DiscussionService {
    
    private final DiscussionRepository discussionRepository;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    public Discussion createDiscussion(List<String> participants) {
        List<Discussion> existingDiscussions = discussionRepository.findDiscussionsByParticipants(participants);
        if (!existingDiscussions.isEmpty()) {
            return existingDiscussions.get(0);
        } else {
            Discussion discussion = new Discussion();
            discussion.setParticipants(participants);
            return discussionRepository.save(discussion);
        }
    }
    

    public List<Discussion> getDiscussionsForUser(String username) {
        return discussionRepository.findDiscussionsByParticipantsContains(username);
    }

    public Discussion getDiscussionById(UUID id) {
        return discussionRepository.findById(id).orElse(null);
    }

}


