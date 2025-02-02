package org.anhvt.springbootelk.service;

import org.anhvt.springbootelk.domain.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class LogService {
//
//    private static final long SIX_HOURS_IN_MS = 6 * 60 * 60 * 1000L;  // 6 hours in milliseconds
//
//    @Autowired
//    private LogEntryRepository logEntryRepository;
//
////    @Autowired
////    public LogService(LogEntryRepository logEntryRepository) {
////        this.logEntryRepository = logEntryRepository;
////    }
//
//    public int getRequestCountInLast6Hours() {
//        long currentTimestamp = System.currentTimeMillis();
//
//        long sixHoursAgoTimestamp = currentTimestamp - SIX_HOURS_IN_MS;
//
//        List<LogEntry> logs = logEntryRepository.findByTimestampAfter(sixHoursAgoTimestamp);
//
//        return logs.size();
//    }
//}
