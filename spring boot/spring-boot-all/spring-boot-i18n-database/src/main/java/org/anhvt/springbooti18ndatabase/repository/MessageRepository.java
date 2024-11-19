package org.anhvt.springbooti18ndatabase.repository;

import org.anhvt.springbooti18ndatabase.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByLanguage_Code(String languageCode);

    Messages findByCodeAndLanguage_Code(String code, String languageCode);
}
