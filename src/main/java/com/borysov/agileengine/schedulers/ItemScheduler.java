package com.borysov.agileengine.schedulers;

import com.borysov.agileengine.models.AuthResponse;
import com.borysov.agileengine.services.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class ItemScheduler {

    private final AuthResponse authResponse;
    private final ItemService itemService;

    @Scheduled(fixedDelay = 300000)
    public void updateData() {
        log.info("updateData");
        if (authResponse.isAuthorized()) {
            itemService.updateData(authResponse.getToken());
        }
    }
}
