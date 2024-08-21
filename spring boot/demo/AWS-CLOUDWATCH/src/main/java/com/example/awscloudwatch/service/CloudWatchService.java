package com.example.awscloudwatch.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.logs.AWSLogs;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.services.logs.model.LogStream;
import com.amazonaws.services.logs.model.PutLogEventsRequest;

@Service
public class CloudWatchService {

    @Autowired
    private AWSLogs cloudWatchlog;

    private static String logGroupName = "my-user-crud-app";
    private static String logStreamName = "my-user-app-log-stream";

    /**
     * Method that logs message to CloudWatch using existing LogGroupName and
     * LogStreamName
     *
     * @param message
     */
    public void logMessageToCloudWatch(String message) {

        List<InputLogEvent> logEvents = new ArrayList<>();

        // Create message log
        InputLogEvent log = new InputLogEvent();
        Calendar calendar = Calendar.getInstance();
        log.setTimestamp(calendar.getTimeInMillis());
        log.setMessage(message);

        logEvents.add(log);

        // Get list log stream
        DescribeLogStreamsRequest logStreamsRequest = new DescribeLogStreamsRequest(logGroupName);

        logStreamsRequest.withLimit(5);
        List<LogStream> logStreamList = new ArrayList<>();
        logStreamList = cloudWatchlog.describeLogStreams(logStreamsRequest).getLogStreams();

        // Get token in log stream
        String token = null;
        for (LogStream logStream : logStreamList) {
            if (logStream.getLogStreamName().equals(logStreamName)) {
                token = logStream.getUploadSequenceToken();
                break;
            }
        }

        // Create and setup log event to log stream, log group
        PutLogEventsRequest putLogEventsRequest = new PutLogEventsRequest();

        putLogEventsRequest.setLogGroupName(logGroupName);
        putLogEventsRequest.setLogStreamName(logStreamName);
        putLogEventsRequest.setLogEvents(logEvents);
        if (token != null) {
            putLogEventsRequest.setSequenceToken(token);
        }

        // Push log
        cloudWatchlog.putLogEvents(putLogEventsRequest);
    }
}
