package com.mq.core.core.messagequeue;

import lombok.Data;

/**
 * @author binary
 */
@Data
public class Message {

    private String topic;

    private String content;

    public Message(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }
}
