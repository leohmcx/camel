package com.leohmcx.camelmicroservicea.bean;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SplitterComponent {
    public List<String> split(String body) {
        return List.of("ABC", "DEF", "GHI", "JKL");
    }
}
