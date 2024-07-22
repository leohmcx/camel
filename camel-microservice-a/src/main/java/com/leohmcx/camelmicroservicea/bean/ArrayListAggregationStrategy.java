package com.leohmcx.camelmicroservicea.bean;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static java.util.Objects.isNull;

@Component
public class ArrayListAggregationStrategy implements AggregationStrategy {
    // 1, 2, 3


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        final var newBody = newExchange.getIn().getBody(ArrayList.class);
        ArrayList<Object> list;

        if (isNull(oldExchange)) {
            list = new ArrayList<>();
            list.add(newBody);
            newExchange.getIn().setBody(list);

            return newExchange;
        } else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            list.add(newBody);

            return oldExchange;
        }
    }
}
