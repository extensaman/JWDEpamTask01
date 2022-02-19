package com.epam.jwd.task01.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A simplified abstraction of a mobile company corporate tariff
 * which extends the parent class with a field which describes
 * the minimum allowable number of subscribers for a given tariff plan
 */

public class CorporateTariff extends Tariff implements Cloneable {
    public static final int DEFAULT_ABONENT_AMOUNT_LOW_BOUND = 10;
    private final Integer abonentAmountLowBound;

    public CorporateTariff() {
        super();
        this.abonentAmountLowBound = DEFAULT_ABONENT_AMOUNT_LOW_BOUND;
    }

    public CorporateTariff(String tariffName,
                           BigDecimal firstConnectionFee,
                           BigDecimal periodicalFee,
                           Integer minuteAmount,
                           Integer SMSAmount,
                           Integer internetTrafficAmount,
                           Integer abonentAmountLowBound) {
        super(tariffName,
                firstConnectionFee,
                periodicalFee,
                minuteAmount,
                SMSAmount,
                internetTrafficAmount);
        this.abonentAmountLowBound = abonentAmountLowBound;
    }

    public Integer getAbonentAmountLowBound() {
        return abonentAmountLowBound;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        if (!super.equals(that)) return false;
        CorporateTariff tariff = (CorporateTariff) that;
        return Objects.equals(this.getTariffName(), tariff.getTariffName()) &&
                Objects.equals(this.getMinuteAmount(), tariff.getMinuteAmount()) &&
                Objects.equals(this.getSMSAmount(), tariff.getSMSAmount()) &&
                Objects.equals(this.getInternetTrafficAmount(), tariff.getInternetTrafficAmount()) &&
                Objects.equals(this.getFirstConnectionFee(), tariff.getFirstConnectionFee()) &&
                Objects.equals(this.getPeriodicalFee(), tariff.getPeriodicalFee()) &&
                Objects.equals(this.abonentAmountLowBound, tariff.abonentAmountLowBound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), abonentAmountLowBound);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", abonentAmountLowBound=" + abonentAmountLowBound +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
