package com.leohmcx.camelmicroserviceb.processor;

import com.leohmcx.camelmicroserviceb.domain.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCurrencyExchangeProcessor {
    public void process(CurrencyExchange exchange) throws Exception {
        final var conversionMultiple = exchange.getConversionMultiple();
        log.info("Do some processing with exchange.getConversionMultiple(): {}", conversionMultiple);
    }
}
