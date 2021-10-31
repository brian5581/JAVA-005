package com.activemq.mqtt.mqtt;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

/**
 * @author binary
 */
public class Listener {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("admin");

        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new org.fusesource.mqtt.client.Listener() {

            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onPublish(UTF8Buffer utf8Buffer, Buffer buffer, Runnable runnable) {
                String message = buffer.utf8().toString();
                System.out.println("Receive message : " + message);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Topic[] topics = {new Topic("mqttTest", QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        System.out.println("subscribe success");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("subscribe failed");
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        synchronized (Listener.class) {
            while (true) {
                Listener.class.wait();
            }
        }
    }
}
