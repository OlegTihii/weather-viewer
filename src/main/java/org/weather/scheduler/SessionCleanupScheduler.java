package org.weather.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.weather.service.SessionService;

@Component
public class SessionCleanupScheduler {

    SessionService sessionService;

    @Autowired
    public SessionCleanupScheduler(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void cleanExpiredSession() {
        sessionService.deleteExpiredSessions();
    }
}
