package com.leohmcx.camelmicroserviceb.controller;

import com.leohmcx.camelmicroserviceb.domain.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.math.BigDecimal.TEN;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(@PathVariable String from, @PathVariable String to) {
        return CurrencyExchange.builder()
                .id(10001L)
                .from("USD")
                .to("INR")
                .conversionMultiple(TEN)
                .build();
    }
}
