package org.marsik.elshelves.currency.dtos;

import java.util.List;

import javax.money.CurrencyUnit;

import lombok.Data;

@Data
public class TimedSumRequest {
    List<TimedMoney> list;
    CurrencyUnit currency;
}
