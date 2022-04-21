package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MqttSubscriberSample {

    public static void main(String[] args) {


        String topicFilter  = "home/#";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
        String broker       = "tcp://192.168.4.2:1883";
        String clientId     = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();


        String  mqtt_user = "Abh29";
        String  mqtt_pass = "secret";

        try {
            MqttClient subscriber = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
            connOpts.setConnectionTimeout(3);
            connOpts.setUserName(mqtt_user);
            connOpts.setPassword(mqtt_pass.toCharArray());
            System.out.println("Connecting to broker: " + broker);

            subscriber.connect(connOpts);

            CountDownLatch receivedSignal = new CountDownLatch(3);

            subscriber.subscribe(topicFilter, qos, (topic, msg) -> {
                byte[] payload = msg.getPayload();
                System.out.println(topic + " : " + msg);

              //  receivedSignal.countDown();
            });
            receivedSignal.await(1, TimeUnit.DAYS);

            subscriber.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason : " + me.getReasonCode());
            System.out.println("msg    : " + me.getMessage());
            System.out.println("loc    : " + me.getLocalizedMessage());
            System.out.println("cause  : " + me.getCause());
            System.out.println("excep  : " + me);
            me.printStackTrace();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}