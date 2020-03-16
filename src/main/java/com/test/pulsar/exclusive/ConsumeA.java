package com.test.pulsar.exclusive;

import com.test.pulsar.util.PulsarClientFactory;
import org.apache.pulsar.client.api.*;

public class ConsumeA {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClientFactory.getClient();
        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionType(SubscriptionType.Exclusive)
                .subscriptionName("my-sub")
                .subscribe();

        while (true){
            Message message = consumer.receive();
            System.out.println(new String(message.getData()));
            consumer.acknowledge(message);
        }
    }
}
