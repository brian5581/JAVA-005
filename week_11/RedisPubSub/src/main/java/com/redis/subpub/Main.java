/*
 * Testing Subscribe order process
 */

package com.redis.subpub;

import redis.clients.jedis.JedisPool;

/**
 * @author binary
 */
public class Main {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool();
        String channelName = "ORDER";

        SubscribeOrder subscribeOrder = new SubscribeOrder(jedisPool, channelName);
        PublishOrder publishOrder = new PublishOrder(jedisPool, channelName);
    }
}
