package org.marsik.elshelves.currency.controllers;

import java.time.LocalDate;
import java.util.Optional;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;
import org.marsik.elshelves.currency.dtos.SumRequest;
import org.marsik.elshelves.currency.dtos.TimedSumRequest;
import org.marsik.elshelves.currency.services.OpenExchangeRatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CurrencyController {
    @Autowired
    OpenExchangeRatesClient openExchangeRatesClient;

    @RequestMapping("/status")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String status() {
        return "ok";
    }

    @RequestMapping("/convert")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MonetaryAmount convert(@RequestParam("currency") String currency,
            @RequestParam("value") String value,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Optional<LocalDate> date) {
        if (!date.isPresent()) {
            return openExchangeRatesClient.convert(FastMoney.parse(value), Monetary.getCurrency(currency));
        } else {
            return openExchangeRatesClient.convert(FastMoney.parse(value), Monetary.getCurrency(currency), date.get());
        }
    }

    @RequestMapping(value = "/sum", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MonetaryAmount sum(@RequestBody SumRequest request) {
        return FastMoney.of(0d, request.getCurrency());
    }

    @RequestMapping(value = "/timedsum", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MonetaryAmount timedSum(@RequestBody TimedSumRequest request) {
        return FastMoney.of(0d, request.getCurrency());
    }
}
