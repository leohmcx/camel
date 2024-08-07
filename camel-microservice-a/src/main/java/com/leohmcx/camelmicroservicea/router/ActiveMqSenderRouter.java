package com.leohmcx.camelmicroservicea.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("timer:activemq?period=10000")
//                .transform().constant("My message for ActiveMQ")
//                .log("${body}")
//                .to("activemq:my-activemq-queue");

//        from("file:files/json")
//                .log("${body}")
//                .to("activemq:my-activemq-queue");

        from("file:files/xml")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");
    }
}
