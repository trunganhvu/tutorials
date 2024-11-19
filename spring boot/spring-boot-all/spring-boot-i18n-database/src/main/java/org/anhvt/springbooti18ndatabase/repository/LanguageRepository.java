package org.anhvt.springbooti18ndatabase.repository;

import org.anhvt.springbooti18ndatabase.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Languages, Long> {
    Languages findByCode(String code);
}
