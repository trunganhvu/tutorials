package org.anhvt.springbootpostgrebackend.queue.pub;

public interface MessagePublisher {

    void publishUser(final String message);

    void publishArticle(final String message);

    void publishSessionBlacklist(final String message);
}
