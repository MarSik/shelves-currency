package org.marsik.elshelves.currency.services;

import java.time.LocalDate;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.convert.ConversionQuery;
import javax.money.convert.ConversionQueryBuilder;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenExchangeRatesClient {
    @Autowired
    private RestTemplate restTemplate;

    public MonetaryAmount convert(MonetaryAmount from, CurrencyUnit to) {
        CurrencyConversion conversion = MonetaryConversions.getConversion(to.getCurrencyCode());
        return from.with(conversion);
    }

    public MonetaryAmount convert(MonetaryAmount from, CurrencyUnit to, LocalDate date) {
        ConversionQuery query = ConversionQueryBuilder.of()
                .setTermCurrency(to)
                .set(LocalDate.class, date)
                .build();
        ExchangeRateProvider provider = MonetaryConversions.getExchangeRateProvider("IDENT", "ECB-HIST", "ECB-HIST90", "ECB", "IMF");
        CurrencyConversion conversion = provider.getCurrencyConversion(query);
        return from.with(conversion);
    }
}
