package org.marsik.elshelves.currency.services;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenExchangeRatesClient {
    /*@Value("${openexchange.token}")
    private String token;*/

    @Autowired
    private RestTemplate restTemplate;

    public MonetaryAmount convert(MonetaryAmount from, CurrencyUnit to) {
        /*
        ResponseEntity<OpenExchangeRatesResult> rates = restTemplate.getForEntity("https://openexchangerates.org/api/latest.json?app_id={}",
                OpenExchangeRatesResult.class,
                token);
        */

        CurrencyConversion conversion = MonetaryConversions.getConversion(to.getCurrencyCode());
        return from.with(conversion);
    }
}
