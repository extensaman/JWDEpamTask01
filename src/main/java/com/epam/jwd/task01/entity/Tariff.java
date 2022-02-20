package com.epam.jwd.task01.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A simplified abstraction of a mobile company tariff that includes properties such as
 * -- tariff's name
 * -- cost of first connection fee
 * -- cost of periodical fee
 * -- minute amount that is included to a tariff
 * -- SMS amount that is included to a tariff
 * -- Internet traffic amount that is included to a tariff
 */

public class Tariff implements Comparable<Tariff> {
    public static final String DEFAULT_TARIFF_NAME = "Base tariff";
    public static final BigDecimal DEFAULT_FIRST_CONNECTION_FEE = BigDecimal.ZERO;
    public static final BigDecimal DEFAULT_PERIODICAL_FEE = BigDecimal.ZERO;
    public static final Integer DEFAULT_MINUTE_AMOUNT = 0;
    public static final Integer DEFAULT_SMS_AMOUNT = 0;
    public static final Integer DEFAULT_INTERNET_TRAFFIC_AMOUNT = 0;
    public static final String ILLEGAL_ARGUMENT_FOR_COMPARING_TARIFFS = "Illegal argument for comparing tariffs";
    public static final String ILLEGAL_TARIFF_NAME_FOR_COMPARING_TARIFFS = "Illegal \'TariffName\' for comparing tariffs";

    private final String tariffName;
    private final BigDecimal firstConnectionFee;
    private final BigDecimal periodicalFee;
    private final Integer minuteAmount;
    private final Integer SMSAmount;
    private final Integer internetTrafficAmount;

    public Tariff() {
        this.tariffName = DEFAULT_TARIFF_NAME;
        this.firstConnectionFee = DEFAULT_FIRST_CONNECTION_FEE;
        this.periodicalFee = DEFAULT_PERIODICAL_FEE;
        this.minuteAmount = DEFAULT_MINUTE_AMOUNT;
        this.SMSAmount = DEFAULT_SMS_AMOUNT;
        this.internetTrafficAmount = DEFAULT_INTERNET_TRAFFIC_AMOUNT;
    }

    public Tariff(String tariffName,
                  BigDecimal firstConnectionFee,
                  BigDecimal periodicalFee,
                  Integer minuteAmount,
                  Integer SMSAmount,
                  Integer internetTrafficAmount) {
        this.tariffName = tariffName;
        this.firstConnectionFee = firstConnectionFee;
        this.periodicalFee = periodicalFee;
        this.minuteAmount = minuteAmount;
        this.SMSAmount = SMSAmount;
        this.internetTrafficAmount = internetTrafficAmount;
    }

    public String getTariffName() {
        return tariffName;
    }

    public BigDecimal getFirstConnectionFee() {
        return firstConnectionFee;
    }

    public BigDecimal getPeriodicalFee() {
        return periodicalFee;
    }

    public Integer getMinuteAmount() {
        return minuteAmount;
    }

    public Integer getSMSAmount() {
        return SMSAmount;
    }

    public Integer getInternetTrafficAmount() {
        return internetTrafficAmount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Tariff tariff = (Tariff) that;
        return Objects.equals(tariffName, tariff.tariffName) &&
                Objects.equals(firstConnectionFee, tariff.firstConnectionFee) &&
                Objects.equals(periodicalFee, tariff.periodicalFee) &&
                Objects.equals(minuteAmount, tariff.minuteAmount) &&
                Objects.equals(SMSAmount, tariff.SMSAmount) &&
                Objects.equals(internetTrafficAmount, tariff.internetTrafficAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffName,
                firstConnectionFee,
                periodicalFee,
                minuteAmount,
                SMSAmount,
                internetTrafficAmount);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffName='" + tariffName + '\'' +
                ", firstConnectionFee=" + firstConnectionFee +
                ", periodicalFee=" + periodicalFee +
                ", minuteAmount=" + minuteAmount +
                ", SMSAmount=" + SMSAmount +
                ", internetTrafficAmount=" + internetTrafficAmount +
                '}';
    }

    @Override
    public int compareTo(Tariff that) {
        if (that == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_FOR_COMPARING_TARIFFS);
        }
        if (getTariffName() == null && that.getTariffName() == null) {
            return 0;
        }
        if (getTariffName() == null || that.getTariffName() == null) {
            throw new IllegalArgumentException(ILLEGAL_TARIFF_NAME_FOR_COMPARING_TARIFFS);
        }
        return getTariffName().compareTo(that.getTariffName());
    }
}
