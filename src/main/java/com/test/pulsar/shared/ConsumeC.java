package com.test.pulsar.shared;

import com.test.pulsar.util.PulsarClientFactory;
import org.apache.pulsar.client.api.*;

public class ConsumeC {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClientFactory.getClient();
        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-sub")
                .subscriptionType(SubscriptionType.Shared)
                .subscribe();

        while (true){
            Message message = consumer.receive();
            System.out.println(new String(message.getData()));
            consumer.acknowledge(message);
        }
    }
}
