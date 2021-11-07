package com.mq.core.core.producer;

/**
 * @author binary
 */
public interface Producer {

    /**
     * Send message to broker
     * @param topic topic
     * @param message message
     * @return send success
     */
    boolean send(String topic, String message);
}
