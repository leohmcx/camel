package com.leohmcx.camelmicroservicea.router.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimeRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log
        // Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer") // queue
                //.transform().constant("My Constant Message")
                .transform().constant("Time now is: " + LocalDateTime.now())
                .to("log:first-timer"); // database
    }
}
