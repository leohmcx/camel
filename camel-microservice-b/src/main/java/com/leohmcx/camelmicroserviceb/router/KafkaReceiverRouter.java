package com.leohmcx.camelmicroserviceb.router;

import org.apache.camel.builder.RouteBuilder;

//@Component
public class KafkaReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:MY_KAFKA_TOPIC")
                .to("log:received-message-from-kafka");
    }
}
