package com.leohmcx.camelmicroservicea.router.a;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
@RequiredArgsConstructor
public class MyFirstTimeRouter extends RouteBuilder {

    private final GetSimpleLogBean simpleLogBean;
    private final GetCurrentTimeBean getCurrentTimeBean;

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log
        // Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer") // queue
                .log("${body}")
                .transform().constant("My Constant Message")
                .log("${body}")
                //.transform().constant("Time now is: " + LocalDateTime.now())
                .bean(getCurrentTimeBean, "getCurrentTime") // transformation
                .log("${body}")
                .bean(simpleLogBean, "getSimpleLog") // processing
                .log("${body}")
                .process(new SimpleLoggingProcess())
                .to("log:first-timer"); // database
    }
}

@Slf4j
class SimpleLoggingProcess implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("SimpleLoggingProcess: {}", exchange);
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is: " + LocalDateTime.now();
    }
}

@Component
@Slf4j
class GetSimpleLogBean {
    public void getSimpleLog(String msg) {
        log.info("Simple Log: {}", msg);
    }
}