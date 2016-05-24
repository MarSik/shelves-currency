package org.marsik.elshelves.currency.dtos;

import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import lombok.Data;

@Data
public class SumRequest {
    List<MonetaryAmount> list;
    CurrencyUnit currency;
}
