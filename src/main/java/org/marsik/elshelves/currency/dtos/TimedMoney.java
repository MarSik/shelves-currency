package org.marsik.elshelves.currency.dtos;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

import lombok.Data;

@Data
public class TimedMoney {
    MonetaryAmount money;
    LocalDate date;
}
