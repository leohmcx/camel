package com.leohmcx.camelmicroservicea.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Pipeline -> Default
        // Content Based Routing - Choice
        // Multicast - Call multiple endpoints

        from("timer:multicast?period=10000")
                .multicast()
                // it can be a kafka queue, activemq queue
                .to("log:something1", "log:something2");

    }
}
