package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Scanner;
import java.util.UUID;

public class MqttPublishSample {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String topic        = "home/topicin";
        String content      = "";
        int qos             = 2;
        String broker       = "tcp://192.168.4.2:1883";
        String clientId     = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();


        String  mqtt_user = "Abh29";
        String  mqtt_pass = "secret";

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
            connOpts.setConnectionTimeout(3);
            connOpts.setUserName(mqtt_user);
            connOpts.setPassword(mqtt_pass.toCharArray());
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            while (true) {
                content = sc.nextLine();
                if (content.equals("EXIT"))
                    break;
                System.out.println("Publishing message: " + content);
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                System.out.println("Message published");
            }
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}