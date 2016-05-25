package org.marsik.elshelves.currency.spring;

import org.springframework.stereotype.Service;
import org.zalando.jackson.datatype.money.MoneyModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        this.registerModule(new MoneyModule());
        this.registerModule(new JavaTimeModule());
    }
}
