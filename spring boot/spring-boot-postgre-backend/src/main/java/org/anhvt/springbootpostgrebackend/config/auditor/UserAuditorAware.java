package org.anhvt.springbootpostgrebackend.config.auditor;

import org.anhvt.springbootpostgrebackend.config.security.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class UserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse("SYSTEM"));
    }
}
