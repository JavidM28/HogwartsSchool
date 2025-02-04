package com.hogwarts.app.service;

import com.hogwarts.app.model.Avatar;
import com.hogwarts.app.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Page<Avatar> getAvatars(int page, int size) {
        return avatarRepository.findAll(PageRequest.of(page, size));
    }

    public Avatar addAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }

    public Avatar updateAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public Avatar findAvatarById(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }
}
