package org.anhvt.springbootpostgrebackend.utils.constant;

public class RedisKey {
    public static final String SESSION_BLACKLIST = "session_blacklist:";

    public static final String TOPIC_USER = "pubsub:user";
    public static final String TOPIC_USERNAME = "pubsub:username";
    public static final String TOPIC_ARTICLE = "pubsub:article";
    public static final String TOPIC_SESSION_BLACKLIST = "pubsub:session-black-list";
    public static final String REGISTERED_USER_KEY = "username:registered";
}
