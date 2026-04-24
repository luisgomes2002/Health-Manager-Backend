package com.health.manager.users.service;

import com.health.manager.users.dto.request.AddSocialMediaRequest;
import com.health.manager.users.dto.response.user.SocialMediaResponse;
import com.health.manager.users.entity.SocialMedia;
import com.health.manager.users.repository.SocialMediaRepository;
import com.health.manager.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AddClientSocialMediaService {

    private final UsersRepository usersRepository;
    private final SocialMediaRepository socialMediaRepository;

    public AddClientSocialMediaService(UsersRepository usersRepository, SocialMediaRepository socialMediaRepository) {
        this.usersRepository = usersRepository;
        this.socialMediaRepository = socialMediaRepository;
    }

    @Transactional
    public SocialMediaResponse addClientSocialMedia(UUID userId, AddSocialMediaRequest request) {
        var user = usersRepository.findDetailedById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getClient() == null) {
            throw new RuntimeException("Usuário não possui perfil de cliente");
        }

        var socialMedia = new SocialMedia();
        socialMedia.setClient(user.getClient());
        socialMedia.setPlatform(request.getPlatform());
        socialMedia.setHandle(request.getHandle());

        var saved = socialMediaRepository.save(socialMedia);
        return toResponse(saved);
    }

    private SocialMediaResponse toResponse(SocialMedia sm) {
        var response = new SocialMediaResponse();
        response.setId(sm.getId());
        response.setPlatform(sm.getPlatform());
        response.setHandle(sm.getHandle());
        response.setCreatedAt(sm.getCreatedAt());
        return response;
    }
}
