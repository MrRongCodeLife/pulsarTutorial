package com.test.pulsar.multitopic;

import com.test.pulsar.util.PulsarClientFactory;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.regex.Pattern;

public class ConsumeA {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClientFactory.getClient();

        Pattern sllTopic = Pattern.compile("persistent://public/default/.*");

        Consumer consumer = client.newConsumer()
                //.topic("my-topic")//Topic names list must be null when use topicsPattern
                .subscriptionName("my-sub")
                .topicsPattern(sllTopic)
                .subscribe();

        while (true){
            Message message = consumer.receive();
            System.out.println(new String(message.getData()));
            consumer.acknowledge(message);
        }
    }
}
