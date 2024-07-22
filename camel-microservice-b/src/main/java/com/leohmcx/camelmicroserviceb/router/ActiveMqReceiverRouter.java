package com.leohmcx.camelmicroserviceb.router;

import com.leohmcx.camelmicroserviceb.domain.CurrencyExchange;
import com.leohmcx.camelmicroserviceb.processor.MyCurrencyExchangeProcessor;
import com.leohmcx.camelmicroserviceb.transformer.MyCurrencyExchangeTransformer;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActiveMqReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
    private final MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
//        from("activemq:my-activemq-queue")
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .bean(myCurrencyExchangeProcessor, "process")
//                .bean(myCurrencyExchangeTransformer, "transform")
//                .to("log:received-message-from-active-mq");

//        from("activemq:my-activemq-xml-queue")
//                .unmarshal()
//                .jacksonXml(CurrencyExchange.class)
//                .bean(myCurrencyExchangeProcessor, "process")
//                .bean(myCurrencyExchangeTransformer, "transform")
//                .to("log:received-message-from-active-mq");

        from("activemq:split-queue")
                .to("log:received-message-from-active-mq");
    }
}
