package com.leohmcx.camelmicroservicea.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} == 'xml'")).log("[XML FILE]") // https://camel.apache.org/components/4.4.x/languages/file-language.html
                    .when(simple("${body} contains 'USD'")).log("[NOT XML FILE - but contains USD]") // https://camel.apache.org/components/4.4.x/languages/simple-language.html#_operator_support
                    .otherwise().log("[NOT XML FILE]")
                .end()
                .log("${messageHistory} ${headers.CamelFileAbsolute} ${file:absolute.path}") // https://camel.apache.org/components/4.4.x/languages/simple-language.html#_operator_support
                .to("file:files/output");
    }
}
