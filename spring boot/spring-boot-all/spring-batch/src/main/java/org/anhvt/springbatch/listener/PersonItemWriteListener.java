package org.anhvt.springbatch.listener;

import org.anhvt.springbatch.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

public class PersonItemWriteListener implements ItemWriteListener<Person> {

    public static final Logger logger = LoggerFactory.getLogger(PersonItemReadListener.class);

    @Override
    public void beforeWrite(Chunk<? extends Person> items) {
        logger.info("Writing started persons list : " + items);
    }

    @Override
    public void afterWrite(Chunk<? extends Person> items) {
        logger.info("Writing completed persons list : " + items);
        ;
    }

    @Override
    public void onWriteError(Exception e, Chunk<? extends Person> items) {
        logger.error("Error in reading the person records " + items);
        logger.error("Error in reading the person records " + e);
    }
}