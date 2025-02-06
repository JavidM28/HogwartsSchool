package com.hogwarts.app.service;

import com.hogwarts.app.model.Avatar;
import com.hogwarts.app.repository.AvatarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AvatarService {
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Page<Avatar> getAvatars(int page, int size) {
        logger.info("Was invoked method getAvatars with page = {} and size = {}", page, size);
        return avatarRepository.findAll(PageRequest.of(page, size));
    }

    public Avatar addAvatar(Avatar avatar) {
        logger.info("Was invoked method addAvatar");
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Long id) {
        logger.info("Was invoked method deleteAvatar with id = {}", id);
        if (!avatarRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existing avatar with id = {}", id);
        }
        avatarRepository.deleteById(id);
    }

    public Avatar updateAvatar(Avatar avatar) {
        logger.info("Was invoked method updateAvatar");
        return avatarRepository.save(avatar);
    }

    public Avatar findAvatarById(Long id) {
        logger.info("Was invoked method findAvatarById with id = {}", id);
        return avatarRepository.findById(id).orElseGet(() -> {
            logger.error("There is no avatar with id = {}", id);
            return null;
        });
    }
}
