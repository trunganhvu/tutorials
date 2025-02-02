package org.anhvt.springbootelk.repository;

import org.anhvt.springbootelk.domain.LogEntry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface LogEntryRepository extends ElasticsearchRepository<LogEntry, String> {
//    // Custom query to find logs in the last 6 hours
//    List<LogEntry> findByTimestampAfter(long timestamp);
//}
