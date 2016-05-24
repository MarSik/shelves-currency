package org.marsik.elshelves.currency.controllers;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;
import org.marsik.elshelves.currency.dtos.SumRequest;
import org.marsik.elshelves.currency.dtos.TimedSumRequest;
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
    @RequestMapping("/status")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String status() {
        return "ok";
    }

    @RequestMapping("/convert")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MonetaryAmount sum(@RequestParam("currency") String currency, @RequestParam("value") String value) {
        return FastMoney.parse(value);
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
