package com.leohmcx.camelmicroservicea.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DeciderBean {
    public boolean isThisConditionMet(String body, @Headers Map<String, String> headers,
                                      @ExchangeProperties Map<String, String> exchangeProperties) {
        log.info("body: {}, \n headers: {}, \n exchangeProperties: {}", body, headers, exchangeProperties);

        return true;
    }
}
