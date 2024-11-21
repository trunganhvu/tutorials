package org.anhvt.springbootjpaauditing.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class UserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse("SYSTEM"));
    }
}
