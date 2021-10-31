package com.activemq.mqtt.mqtt;

import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 * @author binary
 */
public class Publisher {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("admin");

        FutureConnection connection = mqtt.futureConnection();
        connection.connect().await();
        System.out.println("connect");

        int messageAmount = 10;
        UTF8Buffer topic = new UTF8Buffer("mqttTest");
        while (messageAmount > 0) {
            connection.publish(topic, new AsciiBuffer("test message" + messageAmount), QoS.AT_LEAST_ONCE, false);
            System.out.println("send message " + messageAmount);
            messageAmount -= 1;
        }

        connection.disconnect().await();
        System.out.println("disconnect");
    }
}
