package com.health.manager.users.service;

import com.health.manager.users.repository.SocialMediaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteSocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    public DeleteSocialMediaService(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    public void deleteSocialMedia(UUID socialMediaId) {
        if (!socialMediaRepository.existsById(socialMediaId)) {
            throw new RuntimeException("Rede social não encontrada");
        }
        socialMediaRepository.deleteById(socialMediaId);
    }
}
