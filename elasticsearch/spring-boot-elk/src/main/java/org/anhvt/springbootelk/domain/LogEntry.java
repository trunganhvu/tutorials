package org.anhvt.springbootelk.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "logs-*")
@Setter
@Getter
public class LogEntry {
    @Id
    private String id;
    private String level;
    private String message;
    private long timestamp;
}
