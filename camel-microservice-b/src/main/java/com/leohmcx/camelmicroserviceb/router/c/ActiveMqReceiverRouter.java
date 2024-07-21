package com.leohmcx.camelmicroserviceb.router.c;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.TWO;

@Component
@RequiredArgsConstructor
public class ActiveMqReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
    private final MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(myCurrencyExchangeProcessor, "process")
                .bean(myCurrencyExchangeTransformer, "transform")
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

@Component
@Slf4j
class MyCurrencyExchangeProcessor {
    public void process(CurrencyExchange exchange) throws Exception {
        final var conversionMultiple = exchange.getConversionMultiple();
        log.info("Do some processing with exchange.getConversionMultiple(): {}", conversionMultiple);
    }
}

@Component
@Slf4j
class MyCurrencyExchangeTransformer {
    public CurrencyExchange transform(CurrencyExchange exchange) throws Exception {
        final var conversionMultiple = exchange.getConversionMultiple().multiply(TWO);
        log.info("Do some transformation with exchange.getConversionMultiple().multiply(TWO): {}", conversionMultiple);
        exchange.setConversionMultiple(conversionMultiple);

        return exchange;
    }
}