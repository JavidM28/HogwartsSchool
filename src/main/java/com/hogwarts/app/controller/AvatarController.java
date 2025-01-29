package com.hogwarts.app.controller;

import com.hogwarts.app.model.Avatar;
import com.hogwarts.app.service.AvatarService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<Page<Avatar>> getAvatars(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(avatarService.getAvatars(page, size));
    }

    @PostMapping
    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar) {
        return ResponseEntity.ok(avatarService.addAvatar(avatar));
    }

    @PutMapping
    public ResponseEntity<Avatar> updateAvatar(@RequestBody Avatar avatar) {
        Avatar updatedAvatar = avatarService.updateAvatar(avatar);
        if (updatedAvatar == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAvatar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvatar(@PathVariable Long id) {
        avatarService.deleteAvatar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avatar> getAvatarById(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarById(id);
        if (avatar == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avatar);
    }
}
