package com.leohmcx.camelmicroserviceb.transformer;

import com.leohmcx.camelmicroserviceb.domain.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.TWO;

@Component
@Slf4j
public class MyCurrencyExchangeTransformer {
    public CurrencyExchange transform(CurrencyExchange exchange) throws Exception {
        final var conversionMultiple = exchange.getConversionMultiple().multiply(TWO);
        log.info("Do some transformation with exchange.getConversionMultiple().multiply(TWO): {}", conversionMultiple);
        exchange.setConversionMultiple(conversionMultiple);

        return exchange;
    }
}