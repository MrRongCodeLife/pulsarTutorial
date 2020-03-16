package com.test.pulsar.multitopic;

import com.test.pulsar.util.PulsarClientFactory;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class ProduceB {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClientFactory.getClient();
        Producer<byte[]> producer = client.newProducer()
                .topic("my-topc")
                .create();

        String message = "my message";
        for (int i = 0; i < 100; i++) {
            producer.send((message + i).getBytes());
            System.out.println("send:" + message + i);
        }
        producer.close();
        client.close();
    }
}
