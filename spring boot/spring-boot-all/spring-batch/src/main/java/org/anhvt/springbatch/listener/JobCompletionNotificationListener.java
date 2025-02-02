package org.anhvt.springbatch.listener;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private Resource[] resources;

    public JobCompletionNotificationListener(Resource[] resources) {
        this.resources = resources;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("JOB FINISHED !!");
            if (resources == null) {
                log.info("No resource to delete");
                return;
            }

            for (Resource r : resources) {
                try {
                    File file = r.getFile();
                    boolean deleted = file.delete();
                    if (!deleted) {
                        log.warn("Could not delete file: " + file.getPath());
                    } else {
                        log.info("File deleted: " + file.getPath());
                    }
                } catch (IOException e) {
                    log.error("Error accessing file from resource: " + r, e);
                }
            }
        }
    }
}