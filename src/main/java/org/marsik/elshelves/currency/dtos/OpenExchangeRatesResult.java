package org.marsik.elshelves.currency.dtos;

import java.util.Map;

public class OpenExchangeRatesResult {
    String disclaimer;
    String license;
    Long timestamp;
    String base;
    Map<String, Double> rates;
}
