package com.leohmcx.camelmicroservicea.router;

import com.leohmcx.camelmicroservicea.bean.SplitterComponent;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EipPatternsRouter extends RouteBuilder {

    private final SplitterComponent splitterComponent;

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

        from("file:files/csv")
                .convertBodyTo(String.class)
                .split(method(splitterComponent, "split"))
                //.split(body(), ",")
                //.to("log:split-files");
                .to("activemq:split-queue");
    }
}
