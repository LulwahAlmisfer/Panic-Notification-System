package com.panic.panicnotificationservice.controllers;

import com.panic.panicnotificationservice.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity addNotifications(@RequestBody Set<String> notificationIds, @RequestParam Integer clientId) {
        notificationService.addNotifications(notificationIds, clientId);
        return ResponseEntity.ok().build();
    }

}

