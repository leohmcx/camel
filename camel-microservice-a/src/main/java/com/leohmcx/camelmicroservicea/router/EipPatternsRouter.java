package com.leohmcx.camelmicroservicea.router;

import com.leohmcx.camelmicroservicea.bean.ArrayListAggregationStrategy;
import com.leohmcx.camelmicroservicea.bean.DynamicRouterBean;
import com.leohmcx.camelmicroservicea.bean.SplitterComponent;
import com.leohmcx.camelmicroservicea.domain.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EipPatternsRouter extends RouteBuilder {

    private final DynamicRouterBean dynamicRouterBean;
    private final SplitterComponent splitterComponent;
    private final ArrayListAggregationStrategy arrayListAggregationStrategy;

    @Override
    public void configure() throws Exception {
        // Pipeline -> Default
        // Content Based Routing - Choice
        // Multicast - Call multiple endpoints

//        from("timer:multicast?period=10000")
//                .multicast()
//              it can be a kafka queue, activemq queue
//                .to("log:something1", "log:something2");

        /*
          Marshal: Transforms the message body (such as Java object) into a binary or textual format, ready to be wired
          over the network.

          Unmarshal: Transforms data in some binary or textual format (such as received over the network) into a Java
          object; or some other representation according to the data format being used.
         */
//        from("file:files/csv")
//                .unmarshal().csv()
//                .split(body())
                //.to("log:split-files");
//                .to("activemq:split-queue");

//        from("file:files/csv")
//                .convertBodyTo(String.class)
//                .split(method(splitterComponent, "split"))
                //.split(body(), ",")
                //.to("log:split-files");
//                .to("activemq:split-queue");

        /**
         * Aggregation
         * Messages -> Aggregate -> Endpoint
         * to, 3
         */
//        from("file:files/aggregate-json")
//                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
//                .aggregate(simple("${body.to}"), arrayListAggregationStrategy)
//                .completionSize(3)
                //.completionTimeout(HIGHEST)
//                .to("log:aggregate-json");

        /**
         * Routing slip
         */
//        final var routingSlip = "direct:endpoint1,direct:endpoint2";
        //final var routingSlip = "direct:endpoint1,direct:endpoint2,direct:endpoint3";

//        from("timer:routingSlip?period=10000")
//                .transform().constant("My message is hardcoded")
//                .routingSlip(simple(routingSlip));

//        from("direct:endpoint1")
//                .to("log:directEndpoint1");

//        from("direct:endpoint2")
//                .to("log:directEndpoint2");

//        from("direct:endpoint3")
//                .to("log:directEndpoint3");




        /**
         * Dynamic Routing
         *
         * Step 1, Step 2, Step 3
         *
         * It will determine which route to take, or we can take a multiple endpoints
         *
         * Endpoint 1 -> Step 1
         * Endpoint 2 -> Step 2
         * Endpoint 3 & Endpoint 4 -> Step 3
         */

        from("timer:routingSlip?period=10000")
                .transform().constant("My message is hardcoded")
                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint1")
                .to("log:directEndpoint1");

        from("direct:endpoint2")
                .to("log:directEndpoint2");

        from("direct:endpoint3")
                .to("log:directEndpoint3");

    }
}
