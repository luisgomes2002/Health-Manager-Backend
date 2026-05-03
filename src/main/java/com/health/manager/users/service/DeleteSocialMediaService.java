package com.health.manager.users.service;

import com.health.manager.auth.model.AuthenticatedUser;
import com.health.manager.users.entity.SocialMedia;
import com.health.manager.users.repository.SocialMediaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class DeleteSocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    public DeleteSocialMediaService(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    @Transactional
    public void deleteSocialMedia(UUID socialMediaId, AuthenticatedUser currentUser) {
        var socialMedia = socialMediaRepository.findById(socialMediaId)
                .orElseThrow(() -> new RuntimeException("Rede social não encontrada"));

        if (!currentUser.role().equals("ADMIN") && !isOwner(socialMedia, currentUser.userId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sem permissão para deletar esta rede social");
        }

        socialMediaRepository.delete(socialMedia);
    }

    private boolean isOwner(SocialMedia socialMedia, UUID userId) {
        if (socialMedia.getClient() != null) {
            return socialMedia.getClient().getUser().getId().equals(userId);
        }
        if (socialMedia.getProfessional() != null) {
            return socialMedia.getProfessional().getUser().getId().equals(userId);
        }
        return false;
    }
}
