package com.leohmcx.camelmicroserviceb.router.c;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:MY_KAFKA_TOPIC")
                .to("log:received-message-from-kafka");
    }
}
