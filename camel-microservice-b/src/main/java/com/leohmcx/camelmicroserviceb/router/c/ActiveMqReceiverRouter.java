package com.leohmcx.camelmicroserviceb.router.c;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .to("log:received-message-from-active-mq");
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CurrencyExchange {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
}
