package org.anhvt.springbooti18ndatabase.config;

import org.anhvt.springbooti18ndatabase.entity.Messages;
import org.anhvt.springbooti18ndatabase.repository.MessageRepository;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DatabaseMessageSource implements MessageSource {

    private final MessageRepository messageRepository;

    public DatabaseMessageSource(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        String languageCode = locale.getLanguage();  // 'en', 'vi', 'fr', ...
        Messages message = messageRepository.findByCodeAndLanguage_Code(code, languageCode);

        if (message != null) {
            return String.format(message.getMessage(), args);  // Định dạng thông điệp nếu có tham số
        } else {
            return code;  // Fallback khi không tìm thấy thông điệp
        }
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String message = getMessage(code, args, locale);
        return message.equals(code) ? defaultMessage : message;  // Trả về thông điệp mặc định nếu không tìm thấy
    }

    @Override
    public String getMessage(org.springframework.context.MessageSourceResolvable resolvable, Locale locale) {
        return null;
    }
}