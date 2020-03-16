package com.test.pulsar.failover;

import com.test.pulsar.util.PulsarClientFactory;
import org.apache.pulsar.client.api.*;

public class ConsumeB {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClientFactory.getClient();
        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-sub")
                .subscriptionType(SubscriptionType.Failover)
                .subscribe();

        while (true){
            Message message = consumer.receive();
            System.out.println(new String(message.getData()));
            consumer.acknowledge(message);
        }
    }
}
