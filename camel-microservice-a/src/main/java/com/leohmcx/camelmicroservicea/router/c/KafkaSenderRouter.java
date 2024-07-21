package com.leohmcx.camelmicroservicea.router.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/json")
                .log("${body}")
                .to("kafka:MY_KAFKA_TOPIC");
    }
}
