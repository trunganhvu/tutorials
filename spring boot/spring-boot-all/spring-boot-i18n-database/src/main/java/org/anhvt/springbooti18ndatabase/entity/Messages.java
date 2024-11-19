package org.anhvt.springbooti18ndatabase.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;  // Ví dụ: 'greeting', 'welcome'
    private String message;  // Thông điệp (ví dụ: 'Hello, {0}!')

    @ManyToOne
    private Languages language;
}
